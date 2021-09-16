package com.matrixboot.hub.apiserver.infrastructure;

import com.matrixboot.hub.apiserver.domain.entity.ConfigEntity;
import com.matrixboot.hub.apiserver.domain.entity.NodeEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 * create in 2021/9/15 11:38 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Component
@AllArgsConstructor
public class NodeManager {

    public NodeEntity selectNode(ConfigEntity entity) {
        return null;
    }

}
