package com.matrixboot.server.evaluate.infrastructure.interceptor;

import com.matrixboot.server.evaluate.domain.entity.RequestEventEntity;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 请求所有的变量
 *
 * <p>
 * create in 2021/9/18 4:40 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Order(5)
@Component
@AllArgsConstructor
public class DecisionInterceptorAskVector implements IEventInterceptor {

    @Override
    @Async("VectorThreadPool")
    @SneakyThrows(InterruptedException.class)
    public void invoke(@NotNull RequestEventEntity entity) {
        log.info("查询向量");
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(100));
    }
}
