package com.matrixboot.server.evaluate.domain.entity;

import com.matrixboot.strategy.ScenarioMeta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

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
@NoArgsConstructor
@AllArgsConstructor
public class RequestEventEntity implements Serializable {

    private static final long serialVersionUID = -7370886769139063988L;

    private String id;

    private ScenarioMeta scenarioMeta;


}
