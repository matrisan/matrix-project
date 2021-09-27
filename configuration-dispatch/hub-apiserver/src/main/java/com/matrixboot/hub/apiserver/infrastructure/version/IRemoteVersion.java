package com.matrixboot.hub.apiserver.infrastructure.version;

import com.matrixboot.hub.apiserver.domain.entity.ConfigEntity;

/**
 * TODO
 * <p>
 * create in 2021/9/22 1:34 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IRemoteVersion<T> {

    T convertor(ConfigEntity entity);

}
