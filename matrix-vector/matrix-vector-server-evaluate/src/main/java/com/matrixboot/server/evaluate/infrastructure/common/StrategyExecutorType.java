package com.matrixboot.server.evaluate.infrastructure.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 策略执行器的类型名称
 * <p>
 * create in 2022/1/21 7:52 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StrategyExecutorType {

    public static final String ALLOW_LIST = "AllowList";

    public static final String BLOCK_LIST = "BlockList";
}
