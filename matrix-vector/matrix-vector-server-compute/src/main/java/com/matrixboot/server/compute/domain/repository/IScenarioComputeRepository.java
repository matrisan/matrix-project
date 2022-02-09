package com.matrixboot.server.compute.domain.repository;

import com.matrixboot.server.compute.domain.entity.ScenarioComputeEntity;
import com.matrixboot.strategy.ScenarioMeta;

import java.util.List;

/**
 * 计算策略
 * <p>
 * create in 2021/11/9 11:07 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IScenarioComputeRepository {

    /**
     * 查找所有的计算方法
     *
     * @param scenarioMetaEntity 计算实体
     * @return List
     */
    List<ScenarioComputeEntity> findAll(ScenarioMeta scenarioMetaEntity);

}
