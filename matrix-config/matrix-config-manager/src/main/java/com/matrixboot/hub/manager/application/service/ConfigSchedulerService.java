package com.matrixboot.hub.manager.application.service;

import com.matrixboot.hub.manager.application.ConfigSyncCommand;
import com.matrixboot.hub.manager.domain.entity.ConfigEntity;
import com.matrixboot.hub.manager.domain.entity.NodeEntity;
import com.matrixboot.hub.manager.domain.repository.IConfigEntityRepository;
import com.matrixboot.hub.manager.domain.repository.INodeEntityRepository;
import com.matrixboot.hub.manager.infrastructure.calculation.INodeCalculate;
import com.matrixboot.hub.manager.infrastructure.extension.IConfigNodeProcessor;
import com.matrixboot.hub.manager.infrastructure.predicate.IPredicateStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.OrderComparator;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * create in 2021/9/15 11:09 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Service
@AllArgsConstructor
public class ConfigSchedulerService implements InitializingBean {

    private final INodeEntityRepository nodeRepository;

    private final IConfigEntityRepository configRepository;

    private final INodeCalculate defaultNodeCalculate;

    private final List<IConfigNodeProcessor> processors;

    private final Map<String, IPredicateStrategy> strategyMap;

    private final Map<String, INodeCalculate> calculateMap;

    /**
     * 1.获取所有的节点;
     * 2.查找能找到使用的节点;(预选节点)
     * 3.对所有的节点打分;(优选节点)
     * 4.只保留前面两个节点;
     * 5.保存数据到数据库;
     * 6.向节点派发数据.
     *
     * @param command 同步命令
     */
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void syncConfig(@NotNull ConfigSyncCommand command) {
        Optional<ConfigEntity> optional = configRepository.findById(command.getId());
        optional.ifPresent(config -> {
            log.info("需要同步的配置 - {}", config);
            nodeRepository.findAll().stream()
                    .filter(node -> predicateNode(node, config))
                    .map(node -> calculateEachNodeScore(node, config))
                    .sorted((o1, o2) -> o2.getFirst().compareTo(o1.getFirst()))
                    .limit(2)
                    .map(Pair::getSecond)
                    .forEach(node -> executeProcessors(node, config));
        });
    }

    /**
     * 预选策略.
     * 只保留基本能用的节点,
     * 为什么要预选策略?
     * 1.防止有些节点暂时不可以用;
     * 2.防止有些节点是有些大网站独享的;
     * 3.方式有些节点是有特殊用途,如预发布等.
     *
     * @param config 配置实体
     * @param node   节点实体
     * @return boolean
     */
    private boolean predicateNode(@NotNull NodeEntity node, ConfigEntity config) {
        boolean match = node.match(config, strategyMap);
        log.info("预选节点:{} - {} - {}", match, node, config);
        return match;
    }

    /**
     * 对节点进行打分
     *
     * @param config 配置实体
     * @param node   节点实体
     * @return Pair
     */
    @Contract("null, _ -> fail")
    @NotNull
    private Pair<Integer, NodeEntity> calculateEachNodeScore(NodeEntity node, ConfigEntity config) {
        Pair<Integer, NodeEntity> pair = Pair.of(calculate(node, config), node);
        log.info("计算节点评分 - {}", pair);
        return pair;
    }

    /**
     * 计算每个节点对应配置的分值
     *
     * @param node   节点信息
     * @param config 配置信息
     * @return int
     */
    private int calculate(NodeEntity node, @NotNull ConfigEntity config) {
        int calculate = calculateMap.getOrDefault(config.getSelector(), defaultNodeCalculate).calculate(node, config);
        log.info("计算节点评分 - {} - {} - {}", calculate, node, config);
        return calculate;
    }

    /**
     * 扩展点执行
     *
     * @param node   节点信息
     * @param config 配置信息
     */
    private void executeProcessors(NodeEntity node, @NotNull ConfigEntity config) {
        processors.forEach(processor -> processor.configPreProcessor(node, config));
    }

    /**
     * 对扩展点进行排序
     */
    @Override
    public void afterPropertiesSet() {
        OrderComparator.sort(processors);
    }
}
