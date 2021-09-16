package com.matrixboot.hub.apiserver.infrastructure.extension;

import com.matrixboot.hub.apiserver.domain.entity.ConfigEntity;
import com.matrixboot.hub.apiserver.domain.entity.NodeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 发布数据扩展点
 * <p>
 * create in 2021/9/16 11:01 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Component
public class ConfigNodePublishProcessor implements IConfigNodeProcessor {

    @Override
    public void configPreProcessor(NodeEntity nodeEntity, ConfigEntity configEntity) {

    }

    @Override
    public void configPostProcessor(NodeEntity nodeEntity, ConfigEntity configEntity) {

    }
}
