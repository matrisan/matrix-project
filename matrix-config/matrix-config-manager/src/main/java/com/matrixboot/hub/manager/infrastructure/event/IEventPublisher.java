package com.matrixboot.hub.manager.infrastructure.event;

import java.util.Collection;

/**
 * 事件发布器
 * <p>
 * create in 2021/12/24 9:02 AM
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IEventPublisher {

    /**
     * 发布单个数据
     *
     * @param event 单个数据
     * @param topic topic
     */
    void publish(Object event, String topic);

    /**
     * 发布多个数据
     *
     * @param events 单个数据
     * @param topic  topic
     */
    void publish(Collection<?> events, String topic);

}
