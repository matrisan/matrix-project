package com.matrixboot.hub.manager.infrastructure.extension;

import com.matrixboot.hub.manager.domain.entity.MatrixConfigEntity;
import com.matrixboot.hub.manager.domain.entity.MatrixNodeEntity;

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
    void configPreProcessor(MatrixNodeEntity nodeEntity, MatrixConfigEntity configEntity);


    /**
     * 回退/删除方法
     *
     * @param nodeEntity   节点信息
     * @param configEntity 配置信息
     */
    void configPostProcessor(MatrixNodeEntity nodeEntity, MatrixConfigEntity configEntity);


}
