package com.matrixboot.server.decision.application;

import com.matrixboot.server.evaluate.application.EvaluateResult;
import com.matrixboot.server.evaluate.domain.StrategyStatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * create in 2021/9/18 3:56 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

class EvaluateResultTest {

    @Test
    void defaultEvaluateResult() {
        EvaluateResult result = EvaluateResult.defaultEvaluateResult();
        Assertions.assertEquals(0, result.getScore());
        Assertions.assertEquals(StrategyStatusEnum.DISABLE, result.getStatus());
    }
}