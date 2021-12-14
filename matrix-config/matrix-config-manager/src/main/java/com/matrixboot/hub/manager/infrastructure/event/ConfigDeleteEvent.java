package com.matrixboot.hub.manager.infrastructure.event;

import com.matrixboot.hub.manager.domain.entity.ConfigEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * create in 2021/12/10 4:10 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
public class ConfigDeleteEvent extends ApplicationEvent {

    private static final long serialVersionUID = 5634781866559738411L;

    @Getter
    private final ConfigEntity entity;

    public ConfigDeleteEvent(ConfigEntity source) {
        super(source);
        this.entity = source;
    }
}
