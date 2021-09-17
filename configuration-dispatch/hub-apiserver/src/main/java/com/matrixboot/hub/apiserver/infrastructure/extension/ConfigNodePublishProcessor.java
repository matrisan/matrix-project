package com.matrixboot.hub.apiserver.infrastructure.extension;

import com.matrixboot.hub.apiserver.domain.entity.ConfigEntity;
import com.matrixboot.hub.apiserver.domain.entity.NodeEntity;
import com.matrixboot.hub.apiserver.infrastructure.exception.ConfigSyncException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

/**
 * 发布数据
 * <p>
 * create in 2021/9/16 11:01 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@SuppressWarnings("unused")
@Slf4j
@Order(3)
@Component
public class ConfigNodePublishProcessor implements IConfigNodeProcessor {

    @Override
    @Retryable(recover = "configPreProcessorRecover", value = Exception.class, maxAttempts = 5, backoff = @Backoff(delay = 1, multiplier = 1.5))
    public void configPreProcessor(NodeEntity nodeEntity, ConfigEntity configEntity) {
        log.info("下发配置");
    }

    @Override
    @Retryable(recover = "configPostProcessorRecover", value = Exception.class, maxAttempts = 5, backoff = @Backoff(delay = 1, multiplier = 1.5))
    public void configPostProcessor(NodeEntity nodeEntity, ConfigEntity configEntity) {
        log.info("删除下发的配置");
    }

    /**
     * 前置处理
     *
     * @param nodeEntity   节点信息
     * @param configEntity 配置信息
     * @param e            异常信息
     */
    @Recover
    public void configPreProcessorRecover(NodeEntity nodeEntity, ConfigEntity configEntity, @NotNull Exception e) {
        log.info("#health check# unhealthy: " + e.getMessage());
        throw new ConfigSyncException();
    }

    /**
     * 后置处理
     *
     * @param nodeEntity   节点信息
     * @param configEntity 配置信息
     * @param e            异常信息
     */
    @Recover
    public void configPostProcessorRecover(NodeEntity nodeEntity, ConfigEntity configEntity, @NotNull Exception e) {
        log.info("#health check# unhealthy: " + e.getMessage());
        throw new ConfigSyncException();
    }
}
