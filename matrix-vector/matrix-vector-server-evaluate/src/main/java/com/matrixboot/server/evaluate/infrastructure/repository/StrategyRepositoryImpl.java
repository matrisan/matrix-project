package com.matrixboot.server.evaluate.infrastructure.repository;

import com.google.common.collect.Lists;
import com.matrixboot.server.evaluate.domain.repository.IStrategyRepository;
import com.matrixboot.server.evaluate.infrastructure.strategy.AbstractStrategyTemplate;
import com.matrixboot.strategy.ScenarioMeta;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static com.matrixboot.server.evaluate.infrastructure.common.StrategyExecutorType.ALLOW_LIST;
import static com.matrixboot.server.evaluate.infrastructure.common.StrategyExecutorType.BLOCK_LIST;

/**
 * <p>
 * create in 2022/1/21 9:10 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Repository
public class StrategyRepositoryImpl implements IStrategyRepository {

    private ConcurrentHashMap<ScenarioMeta, List<AbstractStrategyTemplate>> strategyTemplateMapAllowList;

    private ConcurrentHashMap<ScenarioMeta, List<AbstractStrategyTemplate>> strategyTemplateMapBlockList;

    @Override
    public List<AbstractStrategyTemplate> findAllByScenarioMeta(ScenarioMeta entity, @NotNull String type) {
        switch (type) {
            case ALLOW_LIST:
                return strategyTemplateMapAllowList.get(entity);
            case BLOCK_LIST:
                return strategyTemplateMapBlockList.get(entity);
            default:
                return Lists.newArrayList();
        }
    }

}
