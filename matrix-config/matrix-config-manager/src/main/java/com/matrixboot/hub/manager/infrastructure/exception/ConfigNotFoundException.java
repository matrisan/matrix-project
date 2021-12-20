package com.matrixboot.hub.manager.infrastructure.exception;

import com.matrixboot.hub.manager.application.ConfigUpdateCommand;
import lombok.Getter;

/**
 * <p>
 * create in 2021/12/10 11:40 AM
 *
 * @author shishaodong
 * @version 0.0.1
 */
public class ConfigNotFoundException extends AbstractConfigSyncException {

    private static final long serialVersionUID = 4838226070636930064L;

    @Getter
    private final ConfigUpdateCommand command;

    public ConfigNotFoundException(ConfigUpdateCommand data) {
        this.command = data;
    }
}
