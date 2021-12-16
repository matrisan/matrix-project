package com.matrixboot.hub.manager.domain.service;

import com.matrixboot.hub.manager.domain.entity.MatrixConfigEntity;
import com.matrixboot.hub.manager.domain.entity.MatrixNodeEntity;
import com.matrixboot.hub.manager.infrastructure.predicate.IPrimaryPredicateStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 预选策略
 * <p>
 * create in 2021/12/9 4:15 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Service
@AllArgsConstructor
public class PrimaryStrategyManagerService {

    private final Map<String, IPrimaryPredicateStrategy> strategyMap;

    /**
     * 预选节点
     *
     * @param list   节点信息
     * @param config 配置信息
     * @return list
     */
    public List<MatrixNodeEntity> primaryPredicate(@NotNull List<MatrixNodeEntity> list, MatrixConfigEntity config) {
        return list.stream().filter(nodeEntity -> predicateNode(nodeEntity, config))
                .collect(Collectors.toList());
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
    private boolean predicateNode(@NotNull MatrixNodeEntity node, MatrixConfigEntity config) {
        boolean match = strategyMap.values().stream()
                .map(strategy -> strategy.match(node, config))
                .anyMatch(flag -> flag = Boolean.TRUE);
        log.info("预选节点:{} - {} - {}", match, node, config);
        return match;
    }

}
