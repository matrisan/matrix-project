package com.matrixboot.server.decision.application;

/**
 * <p>
 * create in 2021/9/18 4:55 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

public enum StrategyStatusEnum {

    /**
     * 启用
     */
    ENABLE,

    /**
     * 不启用
     */
    DISABLE,

    /**
     * 灰度
     */
    CANARY,

    /**
     * 只记录
     */
    WATCH
}
