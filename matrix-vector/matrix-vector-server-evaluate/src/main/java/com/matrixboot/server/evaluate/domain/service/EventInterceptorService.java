package com.matrixboot.server.evaluate.domain.service;

import com.matrixboot.server.evaluate.domain.entity.RequestEventEntity;
import com.matrixboot.server.evaluate.infrastructure.interceptor.IEventInterceptor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 事件预制处理器管理器,做一些初始化工作:
 * 1.创建事件的 ID;
 * 2.发送事件到 kafka;
 * 3.初始化风控结果;
 * 4.查询策略;
 * 5.查询变量;
 *
 * <p>
 * create in 2022/1/20 11:39 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Service
@AllArgsConstructor
public class EventInterceptorService implements InitializingBean {

    private final List<IEventInterceptor> interceptors;

    public void invoke(RequestEventEntity entity) {
        interceptors.forEach(interceptor -> interceptor.invoke(entity));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        OrderComparator.sort(interceptors);
    }
}
