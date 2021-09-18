package com.matrixboot.server.evaluate.application.service;

import com.matrixboot.server.evaluate.application.EvaluateCommand;
import com.matrixboot.server.evaluate.application.EvaluateFactory;
import com.matrixboot.server.evaluate.domain.entity.EvaluateEntity;
import com.matrixboot.server.evaluate.infrastructure.IEventIdGenerator;
import com.matrixboot.server.evaluate.infrastructure.interceptor.IDecisionInterceptor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Service;

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

    public void decision(EvaluateCommand command) {
        EvaluateEntity decision = EvaluateFactory.from(command, generator);
        interceptors.forEach(interceptor -> interceptor.invoke(decision));
    }


    @Override
    public void afterPropertiesSet() {
        OrderComparator.sort(interceptors);
    }
}
