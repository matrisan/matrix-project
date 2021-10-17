package com.matrixboot.server.evaluate.domain.service;

import com.matrixboot.server.evaluate.domain.entity.EvaluateEntity;
import com.matrixboot.server.evaluate.domain.service.label.IMatrixWhiteStrategy;
import com.matrixboot.strategy.MatrixStrategyMetaEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * TODO
 * <p>
 * create in 2021/10/17 12:11 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Component
public class MatrixWhiteStrategyTemplate extends BaseStrategyTemplate implements IMatrixWhiteStrategy {

    private MatrixStrategyMetaEntity metaEntity;

    @Override
    public void evaluate(EvaluateEntity evaluate) {
        if (!metaEntity.isSwitchOn() && ()) {
            return;
        }
        for (String field : metaEntity.getFields()) {


        }


    }
}
