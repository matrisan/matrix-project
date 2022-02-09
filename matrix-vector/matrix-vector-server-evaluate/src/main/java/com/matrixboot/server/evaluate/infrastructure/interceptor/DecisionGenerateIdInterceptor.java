package com.matrixboot.server.evaluate.infrastructure.interceptor;

import com.matrixboot.server.evaluate.domain.entity.RequestEventEntity;
import com.matrixboot.server.evaluate.infrastructure.generator.IEventIdGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>
 * create in 2021/10/16 11:01 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Order(1)
@Component
@AllArgsConstructor
public class DecisionGenerateIdInterceptor implements IEventInterceptor {

    private final IEventIdGenerator generator;

    @Override
    public void invoke(@NotNull RequestEventEntity entity) {
        log.info("初始化 id");
        entity.setId(generator.getId());
    }
}
