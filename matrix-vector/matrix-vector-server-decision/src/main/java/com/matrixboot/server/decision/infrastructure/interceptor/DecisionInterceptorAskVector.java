package com.matrixboot.server.decision.infrastructure.interceptor;

import com.matrixboot.server.decision.domain.entity.DecisionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

/**
 * <p>
 * create in 2021/9/18 4:40 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Order(HIGHEST_PRECEDENCE)
@Component
public class DecisionInterceptorAskVector implements IDecisionInterceptor {

    @Override
    public void invoke(DecisionEntity entity) {
        log.info("查询向量");
    }
}
