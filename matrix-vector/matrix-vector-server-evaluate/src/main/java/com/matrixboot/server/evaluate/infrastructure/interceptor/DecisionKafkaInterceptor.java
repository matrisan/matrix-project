package com.matrixboot.server.evaluate.infrastructure.interceptor;

import com.matrixboot.server.evaluate.domain.entity.EvaluateEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

/**
 * 向 Kafka 发送数据
 * <p>
 * create in 2021/9/28 12:14 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Order(0)
public class DecisionKafkaInterceptor implements IDecisionInterceptor {

    @Override
    public void invoke(EvaluateEntity entity) {
        log.info("发送 kafka");
    }
}
