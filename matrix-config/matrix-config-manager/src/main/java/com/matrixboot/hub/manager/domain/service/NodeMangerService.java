package com.matrixboot.hub.manager.domain.service;

import com.matrixboot.hub.manager.domain.entity.NodeEntity;
import com.matrixboot.hub.manager.infrastructure.node.INodeStatusStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * create in 2021/12/10 12:39 AM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Service
@AllArgsConstructor
public class NodeMangerService {

    private final List<INodeStatusStrategy> list;

    public List<NodeEntity> filter(List<NodeEntity> nodeList) {
        return nodeList.stream()
                .filter(entity -> list.stream().allMatch(strategy -> strategy.filter(entity)))
                .collect(Collectors.toList());
    }
}
