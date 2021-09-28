package com.matrixboot.hub.controller.application;

import com.matrixboot.hub.controller.domain.entity.HeartbeatEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * create in 2021/9/16 11:56 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HeartbeatFactory {

    public static HeartbeatEntity from(HeartbeatCommand command) {
        return new HeartbeatEntity();
    }


}
