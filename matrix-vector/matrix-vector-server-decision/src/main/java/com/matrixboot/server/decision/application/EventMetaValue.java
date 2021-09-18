package com.matrixboot.server.decision.application;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 业务方的基本信息
 * <p>
 * create in 2021/9/18 2:26 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Getter
@Setter
public class EventMetaValue {

    private String projectId;

    private String operateId;
}
