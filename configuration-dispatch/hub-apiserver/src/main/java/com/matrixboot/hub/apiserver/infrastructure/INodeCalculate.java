package com.matrixboot.hub.apiserver.infrastructure;

import com.matrixboot.hub.apiserver.domain.entity.ConfigEntity;
import com.matrixboot.hub.apiserver.domain.entity.NodeEntity;

/**
 * <p>
 * create in 2021/9/16 7:24 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@FunctionalInterface
public interface INodeCalculate {

    int calculate(NodeEntity nodeEntity, ConfigEntity entity);

}
