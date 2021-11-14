package com.matrixboot.server.compute.domain.entity;

import com.matrixboot.compute.ScenarioComputeCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 * <p>
 * create in 2021/11/9 9:13 下午
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
public class ScenarioComputeEntity {

    private String id;

    private String field;

    private String value;

    public ComputeEntity mapKey(ScenarioComputeCommand command) {
        return new ComputeEntity();

    }

}
