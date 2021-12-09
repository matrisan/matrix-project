package com.matrixboot.hub.manager.infrastructure.calculation;

import com.matrixboot.hub.manager.domain.entity.ConfigEntity;
import com.matrixboot.hub.manager.domain.entity.NodeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 * create in 2021/9/16 7:28 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Component("DefaultNodeCalculate")
public class DefaultNodeCalculate implements IOptimizationPredicate {

    @Override
    public int calculate(NodeEntity nodeEntity, ConfigEntity entity) {
        return 0;
    }

}
