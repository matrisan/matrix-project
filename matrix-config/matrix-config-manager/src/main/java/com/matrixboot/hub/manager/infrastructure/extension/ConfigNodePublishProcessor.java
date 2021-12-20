package com.matrixboot.hub.manager.infrastructure.extension;

import com.matrixboot.hub.manager.domain.entity.MatrixConfigEntity;
import com.matrixboot.hub.manager.domain.entity.MatrixNodeEntity;
import com.matrixboot.hub.manager.infrastructure.exception.ConfigSyncException;
import com.matrixboot.hub.manager.infrastructure.version.BaseVersion;
import com.matrixboot.hub.manager.infrastructure.version.IRemoteVersion;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

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

    /**
     * Map 的 key 为 {@link IRemoteVersion} 的子类的名称
     */
    @Resource
    private Map<String, IRemoteVersion<? extends BaseVersion>> versionMap;

    @Override
    @Retryable(recover = "configProcessorRecover", value = Exception.class, maxAttempts = 5, backoff = @Backoff(delay = 1, multiplier = 1.5))
    public void configPreProcessor(@NotNull MatrixNodeEntity nodeEntity, MatrixConfigEntity configEntity) {
        log.info("下发配置");
        IRemoteVersion<? extends BaseVersion> version = versionMap.get(nodeEntity.getNodeVersion());
        version.doTransfer(version.convertor(configEntity));
        configEntity.enable();
    }

    @Override
    @Retryable(recover = "configProcessorRecover", value = Exception.class, maxAttempts = 5, backoff = @Backoff(delay = 1, multiplier = 1.5))
    public void configPostProcessor(@NotNull MatrixNodeEntity nodeEntity, MatrixConfigEntity configEntity) {
        log.info("删除下发的配置");
    }

    /**
     * 前置/后置处理
     *
     * @param nodeEntity   节点信息
     * @param configEntity 配置信息
     * @param e            异常信息
     */
    @Recover
    public void configProcessorRecover(@NotNull MatrixNodeEntity nodeEntity, @NotNull MatrixConfigEntity configEntity, @NotNull Exception e) {
        log.info("出现异常 {}", e.getMessage());
        throw new ConfigSyncException(nodeEntity.getId(), configEntity.getId());
    }

}
