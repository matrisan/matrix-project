package com.matrixboot.hub.manager.infrastructure.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * create in 2021/12/24 10:17 AM
 *
 * @author shishaodong
 * @version 0.0.1
 */
public class ConfigCreateEvent extends ApplicationEvent {

    private static final long serialVersionUID = -4619113061028154419L;

    @Getter
    private final transient Object command;

    public ConfigCreateEvent(Object source) {
        super(source);
        this.command = source;
    }

}
