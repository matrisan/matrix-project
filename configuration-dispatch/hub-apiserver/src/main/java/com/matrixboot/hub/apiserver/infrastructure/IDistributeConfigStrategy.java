package com.matrixboot.hub.apiserver.infrastructure;

import com.matrixboot.hub.apiserver.application.ConfigDistributeCommand;

/**
 * <p>
 * create in 2021/9/16 7:17 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

public interface IDistributeConfigStrategy {

    void distribute(ConfigDistributeCommand command);

}
