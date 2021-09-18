package com.matrixboot.server.decision.application;

import com.matrixboot.server.decision.domain.entity.DecisionEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * create in 2021/9/18 4:21 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DecisionFactory {


    public static DecisionEntity from(DecisionCommand command) {
        return new DecisionEntity();
    }

}
