package com.matrixboot.hub.apiserver.infrastructure;

import com.matrixboot.hub.apiserver.domain.entity.ConfigEntity;
import com.matrixboot.hub.apiserver.domain.entity.NodeEntity;
import com.matrixboot.hub.apiserver.domain.repository.INodeEntityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Map;

/**
 * <p>
 * create in 2021/9/15 11:38 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Component
@AllArgsConstructor
public class NodeManager {

    private final INodeEntityRepository repository;

    private final Map<String, IPredicateStrategy> strategyMap;

    public NodeEntity selectNode(ConfigEntity entity) {
        repository.findAll().stream()
                .filter(nodeEntity -> nodeEntity.match(entity, strategyMap))
                .map(nodeEntity -> Pair.of(calculate(nodeEntity, entity), nodeEntity))
                .sorted(Comparator.comparing(Pair::getFirst))
                .limit(2)
                .map(Pair::getSecond)
                .peek(nodeEntity -> nodeEntity.addNewConfig(entity))
                .forEach(nodeEntity -> {
                });

        return null;
    }

    private int calculate(NodeEntity nodeEntity, ConfigEntity entity) {
        return 1;
    }

}
