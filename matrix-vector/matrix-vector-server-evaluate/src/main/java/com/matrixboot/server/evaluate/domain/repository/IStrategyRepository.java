package com.matrixboot.server.evaluate.domain.repository;

import com.matrixboot.server.evaluate.infrastructure.strategy.AbstractStrategyTemplate;
import com.matrixboot.strategy.ScenarioMeta;

import java.util.List;


/**
 * <p>
 * create in 2022/1/21 5:35 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IStrategyRepository {


    List<AbstractStrategyTemplate> findAllByScenarioMeta(ScenarioMeta entity, String type);

}
