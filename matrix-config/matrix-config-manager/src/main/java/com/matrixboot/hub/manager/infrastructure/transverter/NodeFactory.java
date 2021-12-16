package com.matrixboot.hub.manager.infrastructure.transverter;

import com.matrixboot.hub.manager.application.NodeCreateCommand;
import com.matrixboot.hub.manager.domain.entity.NodeEntity;
import com.matrixboot.hub.manager.domain.value.Capacity;
import com.matrixboot.hub.manager.domain.value.Usage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

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

    /**
     * 转换成节点信息
     *
     * @param command 创建命令
     * @return NodeEntity
     */
    public static NodeEntity from(@NotNull NodeCreateCommand command) {
        return NodeEntity.builder()
                .name(command.getName())
                .nodeVersion(command.getNodeVersion())
                .capacity(new Capacity(command.getCapacity()))
                .resourceUsage(new Usage(0))
                .build();
    }

}
