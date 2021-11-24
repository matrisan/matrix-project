package com.matrixboot.hub.manager.infrastructure.version;

import com.matrixboot.hub.manager.domain.entity.ConfigEntity;

/**
 * 版本转换器
 * <p>
 * create in 2021/9/22 1:34 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IRemoteVersion<T> {

    T convertor(ConfigEntity entity);

}
