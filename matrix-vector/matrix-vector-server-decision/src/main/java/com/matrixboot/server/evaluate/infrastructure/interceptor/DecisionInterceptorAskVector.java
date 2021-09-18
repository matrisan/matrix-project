package com.matrixboot.server.evaluate.infrastructure.interceptor;

import com.matrixboot.server.evaluate.domain.entity.EvaluateEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>
 * create in 2021/9/18 4:40 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Order(11)
@Component
public class DecisionInterceptorAskVector implements IDecisionInterceptor {


    @Override
    public void invoke(EvaluateEntity entity) {
        log.info("查询向量");
    }
}
