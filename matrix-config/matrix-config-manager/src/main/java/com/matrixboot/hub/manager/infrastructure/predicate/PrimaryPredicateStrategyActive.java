package com.matrixboot.hub.manager.infrastructure.predicate;

import com.matrixboot.hub.manager.domain.entity.ConfigEntity;
import com.matrixboot.hub.manager.domain.entity.NodeEntity;
import org.springframework.stereotype.Component;

/**
 * <p>
 * create in 2021/12/10 8:13 AM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Component
public class PrimaryPredicateStrategyActive implements IPrimaryPredicateStrategy {

    @Override
    public boolean match(NodeEntity nodeEntity, ConfigEntity configEntity) {
        return true;
    }
}
