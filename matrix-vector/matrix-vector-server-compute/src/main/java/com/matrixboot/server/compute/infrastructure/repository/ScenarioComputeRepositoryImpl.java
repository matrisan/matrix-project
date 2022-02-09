package com.matrixboot.server.compute.infrastructure.repository;

import com.matrixboot.server.compute.domain.entity.ScenarioComputeEntity;
import com.matrixboot.server.compute.domain.repository.IScenarioComputeRepository;
import com.matrixboot.strategy.ScenarioMeta;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO
 * <p>
 * create in 2021/11/14 10:25 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Component
public class ScenarioComputeRepositoryImpl implements IScenarioComputeRepository {

    @Override
    public List<ScenarioComputeEntity> findAll(ScenarioMeta scenarioMetaEntity) {
        return null;
    }
}
