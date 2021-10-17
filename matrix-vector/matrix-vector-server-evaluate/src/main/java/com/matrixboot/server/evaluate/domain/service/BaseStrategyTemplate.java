package com.matrixboot.server.evaluate.domain.service;

import com.matrixboot.server.evaluate.domain.entity.EvaluateEntity;

import java.io.Serializable;

/**
 * TODO
 * <p>
 * create in 2021/10/16 11:51 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

public abstract class BaseStrategyTemplate implements Serializable {

    public abstract void evaluate(EvaluateEntity evaluate);

}
