package com.matrixboot.server.evaluate.infrastructure.executor;

import com.matrixboot.server.evaluate.application.EvaluateResult;
import com.matrixboot.server.evaluate.infrastructure.strategy.IStrategyTemplate;

/**
 * <p>
 * create in 2022/1/21 5:52 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */

public abstract class AbstractExecutorService {

    /**
     * 执行策略
     *
     * @param strategy 策略
     * @return EvaluateResult
     */
    public EvaluateResult submit(IStrategyTemplate strategy) {
        return null;
    }

    public abstract EvaluateResult doSubmit();


}
