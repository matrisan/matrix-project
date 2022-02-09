package com.matrixboot.server.evaluate.infrastructure.interceptor;

import com.matrixboot.server.evaluate.domain.entity.RequestEventEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>
 * create in 2021/9/19 12:44 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Order(4)
@Component
@AllArgsConstructor
public class DecisionInitStrategyInterceptor implements IEventInterceptor {

//    @Resource
//    private AbstractEvaluateContext context;

    @Override
    public void invoke(RequestEventEntity entity) {
        log.info("初始化策略");
    }
}
