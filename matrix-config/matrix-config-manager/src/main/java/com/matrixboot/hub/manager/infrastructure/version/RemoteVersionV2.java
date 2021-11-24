package com.matrixboot.hub.manager.infrastructure.version;

import com.matrixboot.hub.manager.domain.entity.ConfigEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 * create in 2021/9/22 1:36 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Component("RemoteVersionV2")
public class RemoteVersionV2 implements IRemoteVersion<VersionV2> {

    @Override
    public VersionV2 convertor(ConfigEntity entity) {
        return null;
    }
}
