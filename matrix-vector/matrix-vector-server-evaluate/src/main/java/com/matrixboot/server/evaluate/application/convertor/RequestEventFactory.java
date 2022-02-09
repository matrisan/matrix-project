package com.matrixboot.server.evaluate.application.convertor;

import com.matrixboot.server.evaluate.application.command.EventEvaluateCommand;
import com.matrixboot.server.evaluate.domain.entity.RequestEventEntity;
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
public final class RequestEventFactory {

    public static RequestEventEntity from(@NotNull EventEvaluateCommand command) {
        return RequestEventEntity.builder()
                .scenarioMeta(command.getScenarioMeta())
                .build();
    }

}
