package com.matrixboot.server.evaluate.infrastructure.common;

import com.matrixboot.server.evaluate.application.EvaluateResult;
import com.matrixboot.strategy.ScenarioMeta;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 * <p>
 * create in 2022/1/20 11:55 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SomeKey {

    public static final String RESULT = "SESSION_RESULT_KEY";


    /**
     * 存放最终的结果的 key
     */
    private static final String RESULT_KEY = "EVALUATE_RESULT";

    /**
     * 所有击中策略的 key
     */
    private static final String RESULT_ENABLE_KEY = "EVALUATE_RESULT_ENABLE";

    /**
     * 灰度发布 key
     */
    private static final String RESULT_CANARY_KEY = "EVALUATE_RESULT_CANARY";

    /**
     * 记录 key
     */
    private static final String RESULT_WATCH_KEY = "EVALUATE_RESULT_WATCH";

    /**
     * 不启用 key
     */
    private static final String RESULT_DISABLE_KEY = "EVALUATE_RESULT_DISABLE";


    public static EvaluateResult getEvaluateResult(@NotNull HttpServletRequest httpServletRequest) {
        return (EvaluateResult) httpServletRequest.getAttribute(RESULT);
    }

    public static void setDefaultResult(@NotNull HttpServletRequest httpServletRequest, ScenarioMeta entity) {
        httpServletRequest.setAttribute(RESULT, EvaluateResult.defaultEvaluateResult(entity));
    }


}
