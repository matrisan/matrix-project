package com.matrixboot.hub.manager.domain.service;

import com.matrixboot.hub.manager.domain.entity.MatrixConfigEntity;
import com.matrixboot.hub.manager.domain.entity.MatrixNodeEntity;
import com.matrixboot.hub.manager.infrastructure.extension.IConfigNodeProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.OrderComparator;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * create in 2021/12/9 4:24 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Service
@AllArgsConstructor
public class ConfigProcessorManagerService implements InitializingBean {

    private final List<IConfigNodeProcessor> processors;

    /**
     * 配置与节点的后置处理
     *
     * @param pairList 节点与配置信息
     */
    public void configPreProcessor(@NotNull List<Pair<MatrixNodeEntity, MatrixConfigEntity>> pairList) {
        pairList.forEach(pair -> executeProcessors(pair.getFirst(), pair.getSecond()));
    }

    /**
     * 扩展点执行
     *
     * @param node   节点信息
     * @param config 配置信息
     */
    private void executeProcessors(MatrixNodeEntity node, @NotNull MatrixConfigEntity config) {
        processors.forEach(processor -> processor.configPreProcessor(node, config));
    }

    /**
     * 对扩展点进行排序
     */
    @Override
    public void afterPropertiesSet() {
        OrderComparator.sort(processors);
    }
}
