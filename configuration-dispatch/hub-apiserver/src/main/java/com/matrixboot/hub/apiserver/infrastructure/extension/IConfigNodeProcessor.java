package com.matrixboot.hub.apiserver.infrastructure.extension;

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
public interface IConfigNodeProcessor {

    /**
     * 扩展方法
     *
     * @param nodeEntity   节点信息
     * @param configEntity 配置信息
     */
    void configPreProcessor(NodeEntity nodeEntity, ConfigEntity configEntity);


    /**
     * 回退/删除方法
     *
     * @param nodeEntity   节点信息
     * @param configEntity 配置信息
     */
    void configPostProcessor(NodeEntity nodeEntity, ConfigEntity configEntity);


}
