package com.matrixboot.server.evaluate.infrastructure.executor;

import com.matrixboot.server.evaluate.application.EvaluateResult;
import com.matrixboot.server.evaluate.infrastructure.strategy.IStrategyTemplate;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * create in 2022/1/21 7:47 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Component
public class StrategyExecutorManager {

    @Resource
    private Map<String, AbstractExecutorService> executorServiceMap;

    public EvaluateResult execute(@NotNull IStrategyTemplate strategyTemplate) {
        return executorServiceMap.get(strategyTemplate.getType()).submit(strategyTemplate);
    }

}
