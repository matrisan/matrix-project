package com.matrixboot.hub.apiserver.application.service;

import com.matrixboot.hub.apiserver.application.ConfigDeleteCommand;
import com.matrixboot.hub.apiserver.application.ConfigSyncCommand;
import com.matrixboot.hub.apiserver.domain.entity.ConfigEntity;
import com.matrixboot.hub.apiserver.domain.entity.NodeEntity;
import com.matrixboot.hub.apiserver.domain.repository.IConfigEntityRepository;
import com.matrixboot.hub.apiserver.domain.repository.INodeEntityRepository;
import com.matrixboot.hub.apiserver.infrastructure.calculation.INodeCalculate;
import com.matrixboot.hub.apiserver.infrastructure.extension.IConfigNodeProcessor;
import com.matrixboot.hub.apiserver.infrastructure.predicate.IPredicateStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.OrderComparator;
import org.springframework.data.util.Pair;
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
    @Transactional(rollbackFor = Exception.class)
    public void syncConfig(@NotNull ConfigSyncCommand command) {
        Optional<ConfigEntity> optional = configRepository.findById(command.getId());
        optional.ifPresent(config ->
                nodeRepository.findAll().stream()
                        .filter(node -> predicateNode(node, config))
                        .map(node -> calculateEachNodeScore(node, config))
                        .sorted((o1, o2) -> o2.getFirst().compareTo(o1.getFirst()))
                        .limit(2)
                        .map(Pair::getSecond)
                        .forEach(node -> executeProcessors(node, config))
        );
    }

    /**
     * 删除配置
     * 遍历 configPostProcessor 处理器进行其他处理,如 DNS 解绑, 本地配置删除, 远程配置删除
     *
     * @param command 删除命令
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteConfig(@NotNull ConfigDeleteCommand command) {
        Optional<ConfigEntity> optional = configRepository.findById(command.getId());
        optional.ifPresent(config -> processors.forEach(processor -> processor.configPostProcessor(config.getNode(), config)));
    }

    private final Map<String, IPredicateStrategy> strategyMap;

    /**
     * 预选策略.
     * 只保留基本能用的节点
     *
     * @param config 配置实体
     * @param node   节点实体
     * @return boolean
     */
    private boolean predicateNode(@NotNull NodeEntity node, ConfigEntity config) {
        return node.match(config, strategyMap);
    }

    /**
     * 对节点进行打分
     *
     * @param config 配置实体
     * @param node   节点实体
     * @return Pair
     */
    @Contract("null, _ -> fail")
    private @NotNull Pair<Integer, NodeEntity> calculateEachNodeScore(NodeEntity node, ConfigEntity config) {
        return Pair.of(calculate(node, config), node);
    }

    private final INodeCalculate defaultNodeCalculate;

    private final Map<String, INodeCalculate> calculateMap;

    /**
     * 计算每个节点对应配置的分值
     *
     * @param node   节点信息
     * @param config 配置信息
     * @return int
     */
    private int calculate(NodeEntity node, @NotNull ConfigEntity config) {
        return calculateMap.getOrDefault(config.getSelector(), defaultNodeCalculate)
                .calculate(node, config);
    }

    private final List<IConfigNodeProcessor> processors;

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
