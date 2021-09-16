package com.matrixboot.hub.apiserver.infrastructure;

import com.matrixboot.hub.apiserver.domain.entity.ConfigEntity;
import com.matrixboot.hub.apiserver.domain.entity.NodeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * <p>
 * create in 2021/9/16 7:28 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Primary
@Component("DefaultNodeCalculate")
public class DefaultNodeCalculate implements INodeCalculate {

    @Override
    public int calculate(NodeEntity nodeEntity, ConfigEntity entity) {
        return 0;
    }

}
