package com.matrixboot.server.evaluate.application;

import com.matrixboot.server.evaluate.domain.StrategyStatusEnum;
import com.matrixboot.strategy.ScenarioMeta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

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
public class EvaluateResult implements Serializable {

    private static final long serialVersionUID = -2005570047273041772L;

    private static final EvaluateResult DEFAULT_EVALUATE_RESULT = EvaluateResult.builder()
            .code("Default")
            .score(0)
            .status(StrategyStatusEnum.DISABLE).build();

    private ScenarioMeta meta;

    private String code;

    private Integer score;

    /**
     * 策略的状态,
     * 灰度只针对部分用户生效
     * disable 只记录触发结果
     */
    private StrategyStatusEnum status;

    public void init(@NotNull ScenarioMeta entity) {
        this.meta = entity;
    }


    public static @NotNull EvaluateResult defaultEvaluateResult(@NotNull ScenarioMeta entity) {
        EvaluateResult result = EvaluateResult.builder().code("Default").score(0).status(StrategyStatusEnum.DISABLE).build();
        result.init(entity);
        return result;
    }

    public static EvaluateResult defaultEvaluateResult() {
        return null;
    }


}
