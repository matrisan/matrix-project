package com.matrixboot.hub.manager.infrastructure.predicate;

import com.matrixboot.hub.manager.domain.entity.MatrixConfigEntity;
import com.matrixboot.hub.manager.domain.entity.MatrixNodeEntity;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * 默认预选策略
 * <p>
 * create in 2021/9/16 9:44 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
public class DefaultPredicateStrategy implements IPrimaryPredicateStrategy {

    @Override
    public boolean match(@NotNull MatrixNodeEntity nodeEntity, MatrixConfigEntity configEntity) {
        return nodeEntity.hasCapacity();
    }

}
