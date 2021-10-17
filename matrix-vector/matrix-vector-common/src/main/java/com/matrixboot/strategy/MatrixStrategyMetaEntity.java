package com.matrixboot.strategy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * create in 2021/10/16 11:57 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatrixStrategyMetaEntity extends ScenarioMetaEntity implements Ordered, Serializable {

    /**
     * 策略是否打开
     * true 为打开
     * false 为关闭
     */
    private Boolean switchOn;
    private Boolean enable;

    /**
     * 是否是测试模式,
     * true 不参与评分
     * false 参与评分
     */
    private Boolean testMode;

    /**
     * 策略表达式
     */
    private String expression;

    /**
     * 需要的变量编码
     */
    private List<String> variableCode;

    private List<String> fields;

    /**
     * 灰度相关的信息
     */
    private GrayMeta grayMeta;

    /**
     * 策略类型
     */
    private StrategyTypeEnum strategyType;

    /**
     * 策略的优先级
     */
    private Integer order;


    public boolean isSwitchOn() {
        return !Objects.isNull(switchOn) && switchOn;
    }


    public boolean isEnable() {
        if (!enable) {
            return false;
        }


    }

    @Override
    public int getOrder() {
        return Objects.isNull(order) ? Ordered.LOWEST_PRECEDENCE : order;
    }
}
