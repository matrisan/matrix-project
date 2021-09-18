package com.matrixboot.server.decision.infrastructure.strategy;

import com.matrixboot.server.decision.application.EvaluateResult;
import com.matrixboot.server.decision.application.StrategyStatusEnum;
import com.matrixboot.server.decision.domain.entity.DecisionEntity;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * create in 2021/9/18 2:23 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Getter
@Component
public abstract class AbstractStrategy {

    private String compCode;

    private StrategyStatusEnum status;

    @Resource
    private HttpServletRequest request;

    /**
     * 执行策略
     *
     * @param decision 数据
     * @return EvaluateResult
     */
    public abstract EvaluateResult evaluate(DecisionEntity decision);

}
