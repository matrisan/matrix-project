package com.matrixboot.server.evaluate.infrastructure.interceptor;

import com.matrixboot.server.evaluate.domain.entity.RequestEventEntity;
import com.matrixboot.server.evaluate.infrastructure.common.SomeKey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 向本地线程中设置默认返回值
 * <p>
 * create in 2021/10/16 11:01 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Order(3)
@Component
@AllArgsConstructor
public class DecisionDefaultResultInterceptor implements IEventInterceptor {

    @Resource
    private HttpServletRequest httpServletRequest;

    @Override
    public void invoke(RequestEventEntity entity) {
        log.info("设置默认结果");
        SomeKey.setDefaultResult(httpServletRequest, entity.getScenarioMeta());
    }

}
