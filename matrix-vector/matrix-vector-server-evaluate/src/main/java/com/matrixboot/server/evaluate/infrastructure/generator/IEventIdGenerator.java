package com.matrixboot.server.evaluate.infrastructure.generator;

/**
 * <p>
 * create in 2021/9/19 12:18 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@FunctionalInterface
public interface IEventIdGenerator {

    /**
     * 生成事件的 ID
     *
     * @return String
     */
    String getId();
}
