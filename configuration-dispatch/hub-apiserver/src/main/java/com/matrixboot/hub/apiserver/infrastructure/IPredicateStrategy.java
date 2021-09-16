package com.matrixboot.hub.apiserver.infrastructure;

import com.matrixboot.hub.apiserver.domain.entity.ConfigEntity;
import com.matrixboot.hub.apiserver.domain.entity.NodeEntity;

/**
 * <p>
 * create in 2021/9/15 11:40 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@FunctionalInterface
public interface IPredicateStrategy {

    boolean match(NodeEntity node, ConfigEntity config);

}
