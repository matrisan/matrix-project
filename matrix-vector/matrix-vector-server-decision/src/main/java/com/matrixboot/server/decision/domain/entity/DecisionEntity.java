package com.matrixboot.server.decision.domain.entity;

import com.matrixboot.server.decision.application.EvaluateResult;
import com.matrixboot.server.decision.application.EventDataValue;
import com.matrixboot.server.decision.application.EventMetaValue;
import com.matrixboot.server.decision.application.StrategyStatusEnum;
import com.matrixboot.server.decision.infrastructure.strategy.AbstractStrategy;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * create in 2021/9/18 2:45 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Getter
@Setter
public class DecisionEntity {

    private String id;

    private EventMetaValue eventMeta;

    private EventDataValue eventData;

    /**
     * 默认返回的策略结果
     * 是得分最高的结果
     */
    private EvaluateResult evaluateResult;

    /**
     * 所有击中的策略
     */
    private Map<StrategyStatusEnum, List<EvaluateResult>> evaluateResults;

    /**
     * 所拥有的策略
     */
    private List<AbstractStrategy> strategies;


    public DecisionEntity() {
        evaluateResult = EvaluateResult.defaultEvaluateResult();
        strategies = new ArrayList<>();
        evaluateResults = new HashMap<>();
        initCollection();
    }

    private void initCollection() {
        evaluateResults.put(StrategyStatusEnum.ENABLE, new ArrayList<>(32));
        evaluateResults.put(StrategyStatusEnum.DISABLE, new ArrayList<>(0));
        evaluateResults.put(StrategyStatusEnum.CANARY, new ArrayList<>(0));
    }

    /**
     * 添加策略结果
     * 默认返回最高的结果,会先进判断
     * 如果击中策略也会进行添加到击中的策略列表中
     *
     * @param result 策略结果
     */
    public void addEvaluateResult(@NotNull EvaluateResult result) {
        switch (result.getStatus()) {
            case ENABLE:
                enable(result);
                break;
            case CANARY:
                canary(result);
                break;
            default:
                disable(result);
        }
    }

    private void enable(@NotNull EvaluateResult result) {
        if (evaluateResult.getScore() < result.getScore()) {
            evaluateResult = result;
        }
        extracted(result, StrategyStatusEnum.ENABLE);
    }

    private void canary(@NotNull EvaluateResult result) {
        extracted(result, StrategyStatusEnum.CANARY);
    }

    private void disable(@NotNull EvaluateResult result) {
        extracted(result, StrategyStatusEnum.DISABLE);
    }

    private void extracted(@NotNull EvaluateResult result, StrategyStatusEnum enable) {
        if (result.getScore() > 0) {
            List<EvaluateResult> results = this.evaluateResults.get(enable);
            results.add(result);
        }
    }
}
