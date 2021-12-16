package com.matrixboot.hub.manager.infrastructure.extension;

import com.matrixboot.hub.manager.domain.entity.MatrixConfigEntity;
import com.matrixboot.hub.manager.domain.entity.MatrixNodeEntity;
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
    public void configPreProcessor(MatrixNodeEntity nodeEntity, MatrixConfigEntity configEntity) {
        log.info("绑定 DNS");
    }

    @Override
    public void configPostProcessor(MatrixNodeEntity nodeEntity, MatrixConfigEntity configEntity) {
        log.info("解绑 DNS");
    }
}
