package com.matrixboot.manager.server.domain.service;

/**
 * <p>
 * create in 2021/8/11 2:43 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@FunctionalInterface
public interface IRecordCheckStrategy {

    boolean isRecord(String domain);

}


