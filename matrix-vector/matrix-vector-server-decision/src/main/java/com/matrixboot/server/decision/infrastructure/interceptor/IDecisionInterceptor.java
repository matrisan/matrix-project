package com.matrixboot.server.decision.infrastructure.interceptor;

import com.matrixboot.server.decision.domain.entity.DecisionEntity;

/**
 * <p>
 * create in 2021/9/18 4:28 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@FunctionalInterface
public interface IDecisionInterceptor {

    /**
     * 数据预处理
     *
     * @param entity 数据
     */
    void invoke(DecisionEntity entity);

}
