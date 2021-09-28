package com.matrixboot.hub.apiserver.infrastructure.calculation;

import com.matrixboot.hub.apiserver.domain.entity.ConfigEntity;
import com.matrixboot.hub.apiserver.domain.entity.NodeEntity;

/**
 * 优选策略
 * 优先级筛选节点
 * <p>
 * 节点和 配置 的打分器
 * create in 2021/9/16 7:24 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@FunctionalInterface
public interface INodeCalculate {

    /**
     * 节点信息 配置信息 匹配度打分
     *
     * @param nodeEntity   节点信息
     * @param configEntity 配置信息
     * @return int
     */
    int calculate(NodeEntity nodeEntity, ConfigEntity configEntity);

}
