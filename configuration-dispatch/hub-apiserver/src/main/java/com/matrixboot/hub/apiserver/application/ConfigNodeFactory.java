package com.matrixboot.hub.apiserver.application;

import com.matrixboot.hub.apiserver.domain.entity.ConfigEntity;
import com.matrixboot.hub.apiserver.domain.entity.NodeEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * create in 2021/9/16 7:19 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConfigNodeFactory {

    public static ConfigDistributeCommand from(NodeEntity nodeEntity, ConfigEntity entity) {
        return new ConfigDistributeCommand();
    }

}
