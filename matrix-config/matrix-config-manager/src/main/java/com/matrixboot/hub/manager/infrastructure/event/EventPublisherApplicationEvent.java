package com.matrixboot.hub.manager.infrastructure.event;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * <p>
 * create in 2021/12/24 9:05 AM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Component
public class EventPublisherApplicationEvent implements IEventPublisher, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void publish(Object data, String topic) {
        applicationContext.publishEvent(new ConfigCreateEvent(data));
    }

    @Override
    public void publish(@NotNull Collection<?> dataset, String topic) {
        dataset.forEach(data -> applicationContext.publishEvent(new ConfigCreateEvent(data)));
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
