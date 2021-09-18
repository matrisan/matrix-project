package com.matrixboot.server.decision.infrastructure;

import com.matrixboot.server.decision.application.EvaluateResult;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * create in 2021/9/18 5:48 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

public interface IEvaluateResultContainer {

    /**
     * 添加策略结果
     * 默认返回最高的结果,会先进判断
     * 如果击中策略也会进行添加到击中的策略列表中
     *
     * @param result 策略结果
     */
    void addEvaluateResult(@NotNull EvaluateResult result);

    /**
     * 获取策略的执行结果
     *
     * @return EvaluateResult
     */
    Optional<EvaluateResult> getReturnResult();

    /**
     * 启用的规则
     *
     * @return List
     */
    List<EvaluateResult> getResultEnable();

    /**
     * 灰度的规则
     *
     * @return List
     */
    List<EvaluateResult> getResultCanary();

    /**
     * 记录的规则
     *
     * @return List
     */
    List<EvaluateResult> getResultRecord();

    /**
     * 未启用的规则
     *
     * @return List
     */
    List<EvaluateResult> getResultDisable();
}
