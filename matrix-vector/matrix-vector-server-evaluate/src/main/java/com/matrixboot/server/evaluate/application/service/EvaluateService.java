package com.matrixboot.server.evaluate.application.service;

import com.matrixboot.server.evaluate.application.EvaluateCommand;
import com.matrixboot.server.evaluate.application.EvaluateFactory;
import com.matrixboot.server.evaluate.application.EvaluateResult;
import com.matrixboot.server.evaluate.domain.entity.EvaluateEntity;
import com.matrixboot.server.evaluate.infrastructure.context.IEvaluateContext;
import com.matrixboot.server.evaluate.infrastructure.generator.IEventIdGenerator;
import com.matrixboot.server.evaluate.infrastructure.interceptor.IDecisionInterceptor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * create in 2021/9/18 2:25 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Service
@AllArgsConstructor
public class EvaluateService implements InitializingBean {

    private final List<IDecisionInterceptor> interceptors;

    private final IEventIdGenerator generator;

    private final IEvaluateContext context;

    public EvaluateResult evaluate(EvaluateCommand command) {
        EvaluateEntity decision = EvaluateFactory.from(command, generator);
        interceptors.forEach(interceptor -> interceptor.invoke(decision));
        return context.getFinalResult();
    }

    @Override
    public void afterPropertiesSet() {
        if (!CollectionUtils.isEmpty(interceptors)) {
            OrderComparator.sort(interceptors);
        }
    }
}
