package com.matrixboot.server.evaluate.infrastructure.strategy;

import com.matrixboot.server.evaluate.application.EvaluateResult;
import com.matrixboot.server.evaluate.domain.entity.RequestEventEntity;
import com.matrixboot.server.evaluate.infrastructure.strategy.lable.IBlockList;

/**
 * <p>
 * create in 2022/1/21 5:56 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
public class BlockListStrategy extends AbstractStrategyTemplate implements IBlockList {

    private static final long serialVersionUID = 854871588794065757L;

    @Override
    public EvaluateResult evaluate(RequestEventEntity decision) {
        return null;
    }

    @Override
    public boolean isEnable() {
        return false;
    }
}
