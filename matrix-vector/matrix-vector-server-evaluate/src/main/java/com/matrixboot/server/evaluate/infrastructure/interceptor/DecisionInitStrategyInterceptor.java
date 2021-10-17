package com.matrixboot.server.evaluate.infrastructure.interceptor;

import com.matrixboot.server.evaluate.domain.entity.EvaluateEntity;
import com.matrixboot.server.evaluate.infrastructure.context.AbstractEvaluateContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 * create in 2021/9/19 12:44 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Order(1)
@Component
public class DecisionInitStrategyInterceptor implements IDecisionInterceptor {

    @Resource
    private AbstractEvaluateContext context;

    @Override
    public void invoke(EvaluateEntity entity) {
        log.info("初始化策略");
    }
}
