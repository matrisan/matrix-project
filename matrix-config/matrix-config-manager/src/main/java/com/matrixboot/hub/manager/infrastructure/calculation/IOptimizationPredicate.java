package com.matrixboot.hub.manager.infrastructure.calculation;

import com.matrixboot.hub.manager.domain.entity.MatrixConfigEntity;
import com.matrixboot.hub.manager.domain.entity.MatrixNodeEntity;

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
public interface IOptimizationPredicate {

    /**
     * 节点信息 配置信息 匹配度打分
     *
     * @param nodeEntity   节点信息
     * @param configEntity 配置信息
     * @return int
     */
    int calculate(MatrixNodeEntity nodeEntity, MatrixConfigEntity configEntity);

}
