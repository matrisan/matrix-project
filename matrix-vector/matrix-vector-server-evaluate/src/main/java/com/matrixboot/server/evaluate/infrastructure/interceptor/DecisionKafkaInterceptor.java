package com.matrixboot.server.evaluate.infrastructure.interceptor;

import com.matrixboot.server.evaluate.domain.entity.RequestEventEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 向 Kafka 发送数据
 * <p>
 * create in 2021/9/28 12:14 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Order(2)
@Component
@AllArgsConstructor
public class DecisionKafkaInterceptor implements IEventInterceptor {

    @Override
    @Async("KafkaThreadPool")
    public void invoke(RequestEventEntity entity) {
        log.info("发送 kafka");
    }
}
