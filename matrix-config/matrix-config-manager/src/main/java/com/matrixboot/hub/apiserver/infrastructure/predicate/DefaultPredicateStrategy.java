package com.matrixboot.hub.apiserver.infrastructure.predicate;

import com.matrixboot.hub.apiserver.domain.entity.ConfigEntity;
import com.matrixboot.hub.apiserver.domain.entity.NodeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 默认预选策略
 * <p>
 * create in 2021/9/16 9:44 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Component
public class DefaultPredicateStrategy implements IPredicateStrategy {

    @Override
    public boolean match(NodeEntity nodeEntity, ConfigEntity configEntity) {
        return nodeEntity.hasCapacity();
    }
}
