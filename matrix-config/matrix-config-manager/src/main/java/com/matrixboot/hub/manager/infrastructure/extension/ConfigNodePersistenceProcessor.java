package com.matrixboot.hub.manager.infrastructure.extension;

import com.matrixboot.hub.manager.domain.entity.MatrixConfigEntity;
import com.matrixboot.hub.manager.domain.entity.MatrixNodeEntity;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 数据持久化
 * <p>
 * create in 2021/9/16 11:20 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class ConfigNodePersistenceProcessor implements IConfigNodeProcessor {

    @Override
    public void configPreProcessor(@NotNull MatrixNodeEntity nodeEntity, MatrixConfigEntity configEntity) {
        nodeEntity.addNewConfig(configEntity);
    }

    @Override
    public void configPostProcessor(@NotNull MatrixNodeEntity nodeEntity, MatrixConfigEntity configEntity) {
        nodeEntity.deleteConfig(configEntity);
    }
}
