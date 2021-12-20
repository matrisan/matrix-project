package com.matrixboot.hub.manager.infrastructure.inspect;

import com.matrixboot.hub.manager.domain.entity.MatrixConfigEntity;
import com.matrixboot.hub.manager.infrastructure.exception.ConfigInspectFailureException;

/**
 * 网站配置自检
 *
 * <p>
 * create in 2021/12/20 5:31 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */

public interface IConfigInspect {

    /**
     * 对网站配置进行检查
     *
     * @param config 配置信息
     */
    default void inspect(MatrixConfigEntity config) {
        if (!doInspect(config)) {
            config.disable();
            throw new ConfigInspectFailureException();
        }
    }


    /**
     * 对网站配置进行检查
     *
     * @param config 配置信息
     * @return boolean
     */
    boolean doInspect(MatrixConfigEntity config);

}
