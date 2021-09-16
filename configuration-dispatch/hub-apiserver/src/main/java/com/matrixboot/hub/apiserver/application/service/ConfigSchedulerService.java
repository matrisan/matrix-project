package com.matrixboot.hub.apiserver.application.service;

import com.matrixboot.hub.apiserver.application.ConfigNodeFactory;
import com.matrixboot.hub.apiserver.application.ConfigSyncCommand;
import com.matrixboot.hub.apiserver.domain.entity.ConfigEntity;
import com.matrixboot.hub.apiserver.domain.entity.NodeEntity;
import com.matrixboot.hub.apiserver.domain.repository.IConfigEntityRepository;
import com.matrixboot.hub.apiserver.domain.repository.INodeEntityRepository;
import com.matrixboot.hub.apiserver.infrastructure.IConfigNodeExt;
import com.matrixboot.hub.apiserver.infrastructure.IDistributeConfigStrategy;
import com.matrixboot.hub.apiserver.infrastructure.INodeCalculate;
import com.matrixboot.hub.apiserver.infrastructure.IPredicateStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.OrderComparator;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
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

    private final IConfigEntityRepository configEntityRepository;

    private final INodeEntityRepository nodeEntityRepository;

    private final IDistributeConfigStrategy distributeService;

    /**
     * 1.获取所有的节点;(预选节点)
     * 2.查找能找到使用的节点;
     * 3.对所有的节点打分;(优选节点)
     * 4.只保留前面两个节点;
     * 5.保存数据到数据库;
     * 6.向节点派发数据.
     *
     * @param command 同步命令
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_UNCOMMITTED)
    public void sync(@NotNull ConfigSyncCommand command) {
        Optional<ConfigEntity> optional = configEntityRepository.findById(command.getId());
        optional.ifPresent(configEntity ->
                nodeEntityRepository.findAll().stream()
                        .filter(nodeEntity -> predicateNode(nodeEntity, configEntity))
                        .map(nodeEntity -> calculateEachNodeScore(nodeEntity, configEntity))
                        .sorted((o1, o2) -> o2.getFirst().compareTo(o1.getFirst()))
                        .limit(2)
                        .map(Pair::getSecond)
                        .peek(nodeEntity -> nodeEntity.addNewConfig(configEntity))
                        .peek(nodeEntity -> extension(nodeEntity, configEntity))
                        .forEach(nodeEntity -> distributeService.distribute(ConfigNodeFactory.from(nodeEntity, configEntity))));
    }

    private final Map<String, IPredicateStrategy> strategyMap;

    /**
     * 预选策略.
     * 只保留基本能用的节点
     *
     * @param configEntity 配置实体
     * @param nodeEntity   节点实体
     * @return boolean
     */
    private boolean predicateNode(@NotNull NodeEntity nodeEntity, ConfigEntity configEntity) {
        return nodeEntity.match(configEntity, strategyMap);
    }

    /**
     * 对节点进行打分
     *
     * @param configEntity 配置实体
     * @param nodeEntity   节点实体
     * @return Pair
     */
    @Contract("null, _ -> fail")
    private @NotNull Pair<Integer, NodeEntity> calculateEachNodeScore(NodeEntity nodeEntity, ConfigEntity configEntity) {
        return Pair.of(calculate(nodeEntity, configEntity), nodeEntity);
    }

    private final INodeCalculate defaultNodeCalculate;

    private final Map<String, INodeCalculate> calculateMap;

    /**
     * 计算每个节点对应配置的分值
     *
     * @param nodeEntity   节点信息
     * @param configEntity 配置信息
     * @return int
     */
    private int calculate(NodeEntity nodeEntity, @NotNull ConfigEntity configEntity) {
        return calculateMap.getOrDefault(configEntity.getSelector(), defaultNodeCalculate)
                .calculate(nodeEntity, configEntity);
    }

    private final List<IConfigNodeExt> extList;

    /**
     * 扩展点执行
     *
     * @param nodeEntity   节点信息
     * @param configEntity 配置信息
     */
    private void extension(NodeEntity nodeEntity, @NotNull ConfigEntity configEntity) {
        extList.forEach(iConfigNodeExt -> iConfigNodeExt.extend(nodeEntity, configEntity));
    }

    /**
     * 对扩展点进行排序
     */
    @Override
    public void afterPropertiesSet() {
        OrderComparator.sort(extList);
    }
}