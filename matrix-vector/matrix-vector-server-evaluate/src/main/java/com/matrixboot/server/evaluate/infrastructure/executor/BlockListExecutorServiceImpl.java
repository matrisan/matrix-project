package com.matrixboot.server.evaluate.infrastructure.executor;

import com.matrixboot.server.evaluate.application.EvaluateResult;
import org.springframework.stereotype.Component;

import static com.matrixboot.server.evaluate.infrastructure.common.StrategyExecutorType.BLOCK_LIST;

/**
 * <p>
 * create in 2022/1/21 5:56 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Component(BLOCK_LIST)
public class BlockListExecutorServiceImpl extends AbstractExecutorService {

    @Override
    public EvaluateResult doSubmit() {
        return null;
    }
}
