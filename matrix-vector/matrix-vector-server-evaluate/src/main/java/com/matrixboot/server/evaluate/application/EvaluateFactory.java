package com.matrixboot.server.evaluate.application;

import com.matrixboot.server.evaluate.domain.entity.EvaluateEntity;
import com.matrixboot.server.evaluate.infrastructure.generator.IEventIdGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * <p>
 * create in 2021/9/18 4:21 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EvaluateFactory {

    public static EvaluateEntity from(@NotNull EvaluateCommand command, @NotNull IEventIdGenerator generator) {
        return EvaluateEntity.builder()
                .id(generator.getId())
                .eventMeta(command.getEventMeta())
                .eventData(command.getEventData())
                .build();
    }

}
