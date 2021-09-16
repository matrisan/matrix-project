package com.matrixboot.hub.apiserver.infrastructure.extension;

import com.matrixboot.hub.apiserver.domain.entity.ConfigEntity;
import com.matrixboot.hub.apiserver.domain.entity.NodeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 绑定 DNS
 * <p>
 * create in 2021/9/16 9:41 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Component
public class ConfigNodeDnsBindProcessor implements IConfigNodeProcessor {

    @Override
    public void configPreProcessor(NodeEntity nodeEntity, ConfigEntity configEntity) {
        System.out.println();
    }

    @Override
    public void configPostProcessor(NodeEntity nodeEntity, ConfigEntity configEntity) {
        System.out.println();
    }
}
