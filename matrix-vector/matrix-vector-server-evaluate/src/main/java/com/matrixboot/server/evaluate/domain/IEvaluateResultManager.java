package com.matrixboot.server.evaluate.domain;

import com.matrixboot.server.evaluate.application.EvaluateResult;

/**
 * <p>
 * create in 2022/1/21 8:41 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */

public interface IEvaluateResultManager {

    void addEvaluateResult(EvaluateResult result);

    EvaluateResult getOptimalResult();

}
