package com.matrixboot.hub.monitor.application;

import com.matrixboot.hub.monitor.domain.entity.ConfigStatusEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * TODO
 * <p>
 * create in 2021/12/9 7:47 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConfigStatusFactory {

    public static ConfigStatusEntity from(ConfigCreateCommand command) {
        return ConfigStatusEntity.builder().domain(command.getDomain()).build();
    }

}
