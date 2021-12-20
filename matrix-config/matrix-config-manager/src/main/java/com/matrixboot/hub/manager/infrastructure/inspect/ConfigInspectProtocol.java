package com.matrixboot.hub.manager.infrastructure.inspect;

import com.matrixboot.hub.manager.domain.entity.MatrixConfigEntity;
import org.springframework.stereotype.Component;

/**
 * 检查协议端口是否和已有的有冲突
 * <p>
 * create in 2021/12/20 5:37 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Component
public class ConfigInspectProtocol implements IConfigInspect {

    @Override
    public boolean doInspect(MatrixConfigEntity config) {
        return true;
    }
}
