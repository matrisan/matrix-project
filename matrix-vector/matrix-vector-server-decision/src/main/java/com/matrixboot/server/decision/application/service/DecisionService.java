package com.matrixboot.server.decision.application.service;

import com.matrixboot.server.decision.application.DecisionCommand;
import com.matrixboot.server.decision.application.DecisionFactory;
import com.matrixboot.server.decision.domain.entity.DecisionEntity;
import com.matrixboot.server.decision.infrastructure.interceptor.IDecisionInterceptor;
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
public class DecisionService implements InitializingBean {

    private final List<IDecisionInterceptor> interceptors;

    public void decision(DecisionCommand command) {
        DecisionEntity decision = DecisionFactory.from(command);
        interceptors.forEach(interceptor -> interceptor.invoke(decision));
    }


    @Override
    public void afterPropertiesSet() {
        OrderComparator.sort(interceptors);
    }
}
