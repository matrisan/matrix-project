package com.matrixboot.server.evaluate.interfaces.facade;

import com.matrixboot.common.ReturnVO;
import com.matrixboot.server.evaluate.application.EvaluateCommand;
import com.matrixboot.server.evaluate.application.EvaluateResult;
import com.matrixboot.server.evaluate.application.service.EvaluateService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * create in 2021/9/28 12:20 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@RestController
@AllArgsConstructor
public class EvaluateFacade {

    private final EvaluateService service;

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
    @PostMapping("evaluate")
    public ReturnVO<EvaluateResult> evaluate(@RequestBody EvaluateCommand command) {
        return ReturnVO.success(service.evaluate(command));
    }

    @SuppressWarnings("unused")
    public ReturnVO<EvaluateResult> recover(@RequestBody EvaluateCommand command) {
        log.warn("进入熔断方法 - {}", command);
        return ReturnVO.success(service.getFinalResult(command));
    }

}
