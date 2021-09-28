package com.matrixboot.hub.apiserver.infrastructure.extension;

import com.matrixboot.hub.apiserver.domain.entity.ConfigEntity;
import com.matrixboot.hub.apiserver.domain.entity.NodeEntity;
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
    public void configPreProcessor(@NotNull NodeEntity nodeEntity, ConfigEntity configEntity) {
        nodeEntity.addNewConfig(configEntity);
    }

    @Override
    public void configPostProcessor(@NotNull NodeEntity nodeEntity, ConfigEntity configEntity) {
        nodeEntity.deleteConfig(configEntity);
    }
}
