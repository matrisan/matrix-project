package com.matrixboot.hub.manager.domain.service;

import com.matrixboot.hub.manager.domain.entity.ConfigEntity;
import com.matrixboot.hub.manager.domain.entity.NodeEntity;
import com.matrixboot.hub.manager.infrastructure.calculation.IOptimizationPredicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * create in 2021/12/9 4:20 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Service
@AllArgsConstructor
public class OptimizationStrategyManagerService {

    private final IOptimizationPredicate defaultOptimizationPredicate;

    private final Map<String, IOptimizationPredicate> calculateMap;

    public List<Pair<NodeEntity, ConfigEntity>> optimizationPredicate(@NotNull List<NodeEntity> nodes, ConfigEntity config) {
        return nodes.stream().map(x -> optimizationPredicate(x, config))
                .sorted((o1, o2) -> o2.getFirst().compareTo(o1.getFirst()))
                .limit(2)
                .map(x -> Pair.of(x.getSecond(), config))
                .collect(Collectors.toList());
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
    private Pair<Integer, NodeEntity> optimizationPredicate(NodeEntity node, ConfigEntity config) {
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
        int calculate = calculateMap.getOrDefault(config.getSelector(), defaultOptimizationPredicate).calculate(node, config);
        log.info("计算节点评分 - {} - {} - {}", calculate, node, config);
        return calculate;
    }

}
