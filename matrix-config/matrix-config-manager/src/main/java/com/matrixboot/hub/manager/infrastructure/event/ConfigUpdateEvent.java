package com.matrixboot.hub.manager.infrastructure.event;

import com.matrixboot.hub.manager.domain.entity.MatrixConfigEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * create in 2021/12/10 4:10 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
public class ConfigUpdateEvent extends ApplicationEvent {

    private static final long serialVersionUID = 5634781866559738411L;

    @Getter
    private final MatrixConfigEntity entity;

    public ConfigUpdateEvent(MatrixConfigEntity source) {
        super(source);
        this.entity = source;
    }
}
