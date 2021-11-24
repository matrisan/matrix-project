package com.matrixboot.hub.manager.application;

import com.matrixboot.hub.manager.domain.entity.NodeEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * create in 2021/9/16 9:18 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NodeFactory {

    public static NodeEntity from(NodeCreateCommand command) {
        return NodeEntity.builder().build();
    }

}
