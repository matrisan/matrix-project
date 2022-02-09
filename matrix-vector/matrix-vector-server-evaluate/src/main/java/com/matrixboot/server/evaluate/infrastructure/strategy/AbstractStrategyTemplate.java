package com.matrixboot.server.evaluate.infrastructure.strategy;

import com.matrixboot.server.evaluate.domain.StrategyStatusEnum;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * create in 2021/9/18 2:23 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Getter
public abstract class AbstractStrategyTemplate implements IStrategyTemplate, Serializable {

    private static final long serialVersionUID = 6886599735026080844L;

    protected String compCode;

    protected StrategyStatusEnum status;

    protected String type;

    /**
     * 是否启用
     *
     * @return boolean
     */
    public abstract boolean isEnable();

}
