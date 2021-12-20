package com.matrixboot.hub.manager.application.service;

import com.matrixboot.hub.manager.application.ConfigSyncCommand;
import com.matrixboot.hub.manager.domain.entity.MatrixConfigEntity;
import com.matrixboot.hub.manager.domain.entity.MatrixNodeEntity;
import com.matrixboot.hub.manager.domain.repository.IConfigEntityRepository;
import com.matrixboot.hub.manager.domain.repository.INodeEntityRepository;
import com.matrixboot.hub.manager.domain.service.ConfigProcessorManagerService;
import com.matrixboot.hub.manager.domain.service.OptimizationStrategyManagerService;
import com.matrixboot.hub.manager.domain.service.PrimaryStrategyManagerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
public class ConfigScheduleService {

    private final INodeEntityRepository nodeRepository;

    private final IConfigEntityRepository configRepository;

    private final ConfigProcessorManagerService configSyncService;

    private final PrimaryStrategyManagerService primaryStrategyService;

    private final OptimizationStrategyManagerService optimizationStrategyService;

    /**
     * 1.获取所有的节点;
     * 2.查找能找到使用的节点(预选节点);
     * 3.对所有的节点打分(优选节点);只保留前面两个节点;
     * 4.处理数据:a.保存数据到数据库;b.向节点派发数据.
     *
     * @param command 同步命令
     */
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void syncConfig(@NotNull ConfigSyncCommand command) {
        Optional<MatrixConfigEntity> optional = configRepository.findById(command.getId());
        optional.ifPresent(config -> {
            log.info("需要同步的配置 - {}", config);
            List<MatrixNodeEntity> allNodes = nodeRepository.findAll();
            List<MatrixNodeEntity> predicate = primaryStrategyService.primaryPredicate(allNodes, config);
            List<Pair<MatrixNodeEntity, MatrixConfigEntity>> pairList = optimizationStrategyService.optimizationPredicate(predicate, config);
            configSyncService.configPreProcessor(pairList);
        });
    }
}
