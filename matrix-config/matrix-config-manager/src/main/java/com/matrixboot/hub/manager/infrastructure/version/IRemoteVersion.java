package com.matrixboot.hub.manager.infrastructure.version;

import com.matrixboot.hub.manager.domain.entity.MatrixConfigEntity;

/**
 * 版本转换器
 * <p>
 * create in 2021/9/22 1:34 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IRemoteVersion<T> {

    /**
     * 配置转换器
     *
     * @param entity 配置实体类
     * @return T
     */
    T convertor(MatrixConfigEntity entity);

    /**
     * 具体执行传输数据
     *
     * @param data 不同版本的数据
     */
    void doTransfer(BaseVersion data);

}
