package com.matrixboot.server.evaluate.infrastructure.strategy;

import com.matrixboot.server.evaluate.application.EvaluateResult;
import com.matrixboot.server.evaluate.domain.entity.RequestEventEntity;
import com.matrixboot.server.evaluate.infrastructure.strategy.lable.IAllowList;

/**
 * <p>
 * create in 2022/1/21 5:56 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
public class AllowListStrategy extends AbstractStrategyTemplate implements IAllowList {

    private static final long serialVersionUID = -1200914388817264479L;

    @Override
    public EvaluateResult evaluate(RequestEventEntity decision) {
        return null;
    }

    @Override
    public boolean isEnable() {
        return false;
    }
}
