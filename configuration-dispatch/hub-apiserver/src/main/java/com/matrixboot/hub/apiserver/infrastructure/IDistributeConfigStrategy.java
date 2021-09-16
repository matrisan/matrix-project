package com.matrixboot.hub.apiserver.infrastructure;

import com.matrixboot.hub.apiserver.application.ConfigDistributeCommand;

/**
 * 配置同步的策略
 * 可以有 OpenFeign 实现, Netty 实现, zk 实现
 * create in 2021/9/16 7:17 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

public interface IDistributeConfigStrategy {

    /**
     * 同步数据
     *
     * @param command 派发数据命令
     */
    void distribute(ConfigDistributeCommand command);

}
