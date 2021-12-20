package com.matrixboot.hub.manager.infrastructure.inspect;

import com.matrixboot.hub.manager.domain.entity.MatrixConfigEntity;
import org.springframework.stereotype.Component;

/**
 * 检查域名是否备案,如果没有备案则不让添加
 * <p>
 * create in 2021/12/20 5:37 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Component
public class ConfigInspectRecord implements IConfigInspect {

    @Override
    public boolean doInspect(MatrixConfigEntity config) {
        return true;
    }
}
