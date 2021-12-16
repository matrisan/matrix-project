package com.matrixboot.hub.manager.application.service;

import com.matrixboot.hub.manager.application.NodeCreateCommand;
import com.matrixboot.hub.manager.application.NodeUpdateCommand;
import com.matrixboot.hub.manager.domain.entity.MatrixNodeEntity;
import com.matrixboot.hub.manager.domain.repository.INodeEntityRepository;
import com.matrixboot.hub.manager.domain.value.Capacity;
import com.matrixboot.hub.manager.infrastructure.transverter.MatrixNodeFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
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
@Validated
@AllArgsConstructor
public class NodeManagerService {

    private final INodeEntityRepository repository;

    /**
     * 创建节点
     *
     * @param command 创建命令
     */
    public void createNode(@Valid NodeCreateCommand command) {
        repository.save(MatrixNodeFactory.from(command));
    }

    /**
     * 更新节点信息
     *
     * @param command 更新命令
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(@NotNull NodeUpdateCommand command) {
        Optional<MatrixNodeEntity> optional = repository.findById(command.getId());
        optional.ifPresent(node -> {
            node.setName(command.getName());
            node.setCapacity(new Capacity(command.getCapacity()));
            node.setNodeVersion(command.getNodeVersion());
        });
    }

}
