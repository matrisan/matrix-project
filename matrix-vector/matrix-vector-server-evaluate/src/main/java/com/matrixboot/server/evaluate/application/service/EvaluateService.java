package com.matrixboot.server.evaluate.application.service;

import com.matrixboot.server.evaluate.application.EvaluateResult;
import com.matrixboot.server.evaluate.application.command.EventEvaluateCommand;
import com.matrixboot.server.evaluate.application.convertor.RequestEventFactory;
import com.matrixboot.server.evaluate.domain.entity.RequestEventEntity;
import com.matrixboot.server.evaluate.domain.service.EventEvaluateService;
import com.matrixboot.server.evaluate.domain.service.EventInterceptorService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * create in 2021/9/18 2:25 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Service
@Validated
@AllArgsConstructor
public class EvaluateService {

    private final EventInterceptorService interceptorService;

    private final EventEvaluateService eventEvaluateService;

    /**
     * 执行风控评估命令
     *
     * @param command 执行命令
     * @return EvaluateResult
     */
    @HystrixCommand(fallbackMethod = "recover",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "80")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "200"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "1"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "10000"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "4800")
            }
    )
    public EvaluateResult evaluate(@Valid EventEvaluateCommand command) {
        RequestEventEntity decision = RequestEventFactory.from(command);
        interceptorService.invoke(decision);
        return eventEvaluateService.evaluate(decision);
    }

    /**
     * 当发生熔断时,调用这个方法
     *
     * @param command 传入的数据
     * @return EvaluateResult
     */
    public EvaluateResult recover(EventEvaluateCommand command) {
        log.warn("进入熔断方法 - {}", command);
        return eventEvaluateService.getFinalResult();
    }

}
