package com.matrixboot.hub.manager.infrastructure.node;

import com.matrixboot.hub.manager.domain.entity.NodeEntity;

/**
 * TODO
 * <p>
 * create in 2021/12/10 12:40 AM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@FunctionalInterface
public interface INodeStatusStrategy {

    boolean filter(NodeEntity entity);

}
