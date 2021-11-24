package com.matrixboot.hub.manager.infrastructure.transverter;

import com.matrixboot.hub.manager.application.ConfigCreateCommand;
import com.matrixboot.hub.manager.domain.entity.ConfigEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * <p>
 * create in 2021/9/15 10:56 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConfigFactory {

    public static ConfigEntity create(@NotNull ConfigCreateCommand command) {
        return ConfigEntity.builder()
                .namespace(command.getNamespace())
                .domain(command.getDomain())
                .source(command.getSource())
                .build();
    }

}
