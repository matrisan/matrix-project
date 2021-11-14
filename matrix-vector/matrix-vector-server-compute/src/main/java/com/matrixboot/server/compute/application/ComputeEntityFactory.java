package com.matrixboot.server.compute.application;

import com.matrixboot.compute.ScenarioComputeCommand;
import com.matrixboot.server.compute.domain.entity.ComputeEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * TODO
 * <p>
 * create in 2021/11/14 10:55 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ComputeEntityFactory {


    public static ComputeEntity from(ScenarioComputeCommand command) {
        return new ComputeEntity();
    }

}
