package com.matrixboot.server.evaluate.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 使用原型模式加快对象的创建
 *
 * <p>
 * create in 2021/9/18 3:02 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EvaluateResult implements Cloneable {

    private static final EvaluateResult DEFAULT_EVALUATE_RESULT = EvaluateResult.builder()
            .code("Default").score(0).status(StrategyStatusEnum.DISABLE).build();

    private String code;

    private Integer score;

    /**
     * 策略的状态,
     * 灰度只针对部分用户生效
     * disable 只记录触发结果
     */
    private StrategyStatusEnum status;

    public static EvaluateResult defaultEvaluateResult() {
        return DEFAULT_EVALUATE_RESULT.clone();
    }

    @Override
    @SneakyThrows(CloneNotSupportedException.class)
    public EvaluateResult clone() {
        return (EvaluateResult) super.clone();
    }
}
