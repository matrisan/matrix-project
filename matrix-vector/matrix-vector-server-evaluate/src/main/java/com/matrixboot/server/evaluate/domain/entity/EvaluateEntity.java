package com.matrixboot.server.evaluate.domain.entity;

import com.matrixboot.server.evaluate.application.EventDataValue;
import com.matrixboot.server.evaluate.application.EventMetaValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * create in 2021/9/18 2:45 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Getter
@Setter
@Builder
@AllArgsConstructor
public class EvaluateEntity {

    private String id;

    private EventMetaValue eventMeta;

    private EventDataValue eventData;


}
