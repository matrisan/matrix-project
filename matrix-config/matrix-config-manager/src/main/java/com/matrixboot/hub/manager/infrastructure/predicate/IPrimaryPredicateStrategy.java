package com.matrixboot.hub.manager.infrastructure.predicate;

import com.matrixboot.hub.manager.domain.entity.ConfigEntity;
import com.matrixboot.hub.manager.domain.entity.NodeEntity;

/**
 * 预选策略
 * <p>
 * create in 2021/9/15 11:40 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@FunctionalInterface
public interface IPrimaryPredicateStrategy {

    /**
     * 预选节点
     * 加入候选节点的返回 true, 不加入候选节点的返回 false.
     *
     * @param nodeEntity   节点信息
     * @param configEntity 配置信息
     * @return boolean
     */
    boolean match(NodeEntity nodeEntity, ConfigEntity configEntity);

}
