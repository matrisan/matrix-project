package com.matrixboot.server.compute.domain.repository;

import com.matrixboot.server.compute.domain.entity.ScenarioComputeEntity;
import com.matrixboot.strategy.ScenarioMetaEntity;

import java.util.List;

/**
 * TODO
 * <p>
 * create in 2021/11/9 11:07 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IScenarioComputeRepository {

    /**
     * @param scenarioMetaEntity
     * @return
     */
    List<ScenarioComputeEntity> findAll(ScenarioMetaEntity scenarioMetaEntity);

}
