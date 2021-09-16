package com.matrixboot.hub.apiserver.application;

/**
 * 配置同步类型
 * <p>
 * create in 2021/9/16 8:36 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

public enum ConfigSyncTypeEnum {

    /**
     * 创建,刚刚添加网站
     */
    CREATE,

    /**
     * 重新创建,一般是配置重复下发
     */
    RECREATE,

    /**
     * 重新调度
     */
    RESCHEDULE
}
