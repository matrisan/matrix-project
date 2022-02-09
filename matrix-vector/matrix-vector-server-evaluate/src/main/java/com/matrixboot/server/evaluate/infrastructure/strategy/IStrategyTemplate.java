package com.matrixboot.server.evaluate.infrastructure.strategy;

import com.matrixboot.server.evaluate.application.EvaluateResult;
import com.matrixboot.server.evaluate.domain.StrategyStatusEnum;
import com.matrixboot.server.evaluate.domain.entity.RequestEventEntity;

/**
 * 策略最顶层接口
 * <p>
 * create in 2022/1/21 7:50 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IStrategyTemplate {

    /**
     * 执行策略
     *
     * @param decision 数据
     * @return EvaluateResult
     */
    EvaluateResult evaluate(RequestEventEntity decision);

    /**
     * 策略的类型,黑,白,统计
     *
     * @return String
     */
    String getType();

    /**
     * 策略状态
     *
     * @return String
     */
    StrategyStatusEnum getStatus();

}
