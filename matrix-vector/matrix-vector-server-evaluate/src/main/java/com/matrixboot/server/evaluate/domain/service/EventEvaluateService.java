package com.matrixboot.server.evaluate.domain.service;

import com.google.common.collect.Lists;
import com.matrixboot.server.evaluate.application.EvaluateResult;
import com.matrixboot.server.evaluate.domain.IEvaluateResultManager;
import com.matrixboot.server.evaluate.domain.entity.RequestEventEntity;
import com.matrixboot.server.evaluate.domain.repository.IStrategyRepository;
import com.matrixboot.server.evaluate.infrastructure.strategy.AbstractStrategyTemplate;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.matrixboot.server.evaluate.infrastructure.common.StrategyExecutorType.ALLOW_LIST;
import static com.matrixboot.server.evaluate.infrastructure.common.StrategyExecutorType.BLOCK_LIST;

/**
 * <p>
 * create in 2022/1/21 8:36 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Service
@AllArgsConstructor
public class EventEvaluateService {

    private static final List<String> TYPES = Lists.newArrayList(ALLOW_LIST, BLOCK_LIST);

    private final IEvaluateResultManager evaluateResultManager;

    private final IStrategyRepository strategyRepository;

    public EvaluateResult evaluate(@NotNull RequestEventEntity entity) {
        for (String type : TYPES) {
            List<AbstractStrategyTemplate> allByScenarioMeta = strategyRepository.findAllByScenarioMeta(entity.getScenarioMeta(), type);
            allByScenarioMeta.forEach(template -> template.evaluate(entity));
        }
        return getFinalResult();
    }

    public EvaluateResult getFinalResult() {
        return evaluateResultManager.getOptimalResult();
    }
}
