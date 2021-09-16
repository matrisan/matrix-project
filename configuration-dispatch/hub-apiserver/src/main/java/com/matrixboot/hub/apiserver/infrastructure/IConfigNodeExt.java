package com.matrixboot.hub.apiserver.infrastructure;

import com.matrixboot.hub.apiserver.domain.entity.ConfigEntity;
import com.matrixboot.hub.apiserver.domain.entity.NodeEntity;

/**
 * 配置同步的扩展节点
 * 可以 DNS 绑定
 *
 * <p>
 * create in 2021/9/16 8:48 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@FunctionalInterface
public interface IConfigNodeExt {

    /**
     * 扩展方法
     *
     * @param nodeEntity   节点信息
     * @param configEntity 配置信息
     */
    void extend(NodeEntity nodeEntity, ConfigEntity configEntity);
}
