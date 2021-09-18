package com.matrixboot.server.decision.application;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * create in 2021/9/18 2:25 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Getter
@Setter
public class DecisionCommand {

    private EventMetaValue eventMeta;

    private EventDataValue eventData;

}
