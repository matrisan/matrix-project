package com.matrixboot.server.evaluate.infrastructure;

import com.matrixboot.server.evaluate.application.EvaluateResult;
import com.matrixboot.server.evaluate.domain.entity.EvaluateEntity;
import com.matrixboot.server.evaluate.domain.service.BaseStrategyTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 策略管理器
 * <p>
 * create in 2021/10/16 11:30 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
public abstract class AbstractStrategyManager {

    private Map<String, BaseStrategyTemplate> whiteList;

    private Map<String, BaseStrategyTemplate> blackList;

    private Map<String, BaseStrategyTemplate> serviceProtection;

    private Map<String, BaseStrategyTemplate> dependable;

    public EvaluateResult evaluateResult(EvaluateEntity entity) {


    }


}
