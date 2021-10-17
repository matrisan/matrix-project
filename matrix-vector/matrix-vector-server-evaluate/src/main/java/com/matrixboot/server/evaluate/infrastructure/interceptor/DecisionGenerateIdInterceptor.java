package com.matrixboot.server.evaluate.infrastructure.interceptor;

import com.matrixboot.server.evaluate.domain.entity.EvaluateEntity;
import com.matrixboot.server.evaluate.infrastructure.generator.IEventIdGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * TODO
 * <p>
 * create in 2021/10/16 11:01 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Order(0)
@Component
@AllArgsConstructor
public class DecisionGenerateIdInterceptor implements IDecisionInterceptor {

    private final IEventIdGenerator generator;

    @Override
    public void invoke(EvaluateEntity entity) {
        entity.setId(generator.getId());
    }
}
