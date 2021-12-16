package com.matrixboot.hub.manager.infrastructure.transverter;

import com.matrixboot.hub.manager.application.ConfigCreateCommand;
import com.matrixboot.hub.manager.domain.entity.MatrixConfigEntity;
import com.matrixboot.hub.manager.domain.value.Resources;
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
public final class MatrixConfigFactory {

    /**
     * 创建 MatrixConfigEntity
     *
     * @param command ConfigCreateCommand
     * @return MatrixConfigEntity
     */
    public static MatrixConfigEntity create(@NotNull ConfigCreateCommand command) {
        return MatrixConfigEntity.builder()
                .namespace(command.getNamespace())
                .domain(command.getDomain())
                .source(command.getSource())
                .resources(new Resources(command.getResources()))
                .build();
    }

}
