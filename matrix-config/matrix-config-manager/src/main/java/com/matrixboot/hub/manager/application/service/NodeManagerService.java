package com.matrixboot.hub.manager.application.service;

import com.matrixboot.hub.manager.application.NodeCreateCommand;
import com.matrixboot.hub.manager.infrastructure.transverter.NodeFactory;
import com.matrixboot.hub.manager.application.NodeUpdateCommand;
import com.matrixboot.hub.manager.domain.entity.NodeEntity;
import com.matrixboot.hub.manager.domain.repository.INodeEntityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 节点管理器
 * <p>
 * create in 2021/9/16 9:17 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Service
@AllArgsConstructor
public class NodeManagerService {

    private final INodeEntityRepository repository;

    public void createNode(NodeCreateCommand command) {
        repository.save(NodeFactory.from(command));
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(@NotNull NodeUpdateCommand command) {
        Optional<NodeEntity> optional = repository.findById(command.getId());
        optional.ifPresent(node -> {
            //TODO UPDATE
        });

    }

}
