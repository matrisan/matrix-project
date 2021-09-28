package com.matrixboot.server.evaluate.infrastructure.interceptor;

import com.matrixboot.server.evaluate.domain.entity.EvaluateEntity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * create in 2021/9/18 4:40 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Order(11)
@Component
public class DecisionInterceptorAskVector implements IDecisionInterceptor {


    @SneakyThrows(InterruptedException.class)
    @Override
    public void invoke(@NotNull EvaluateEntity entity) {
        log.info("查询向量");
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(100));
        entity.getLatch().countDown();
    }
}
