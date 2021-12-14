package com.matrixboot.hub.manager.application.service;

import com.matrixboot.hub.manager.application.ConfigCreateCommand;
import com.matrixboot.hub.manager.domain.IConfigView;
import com.matrixboot.hub.manager.domain.entity.ConfigEntity;
import com.matrixboot.hub.manager.domain.repository.IConfigEntityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * <p>
 * create in 2021/12/10 11:51 AM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@SpringBootTest
class ConfigManagerServiceTest {

    @Resource
    private ConfigManagerService configManagerService;

    @Resource
    private IConfigEntityRepository repository;

    @Test
    void findAll() {
        Page<IConfigView> page = configManagerService.findAll(Pageable.ofSize(10));
        Assertions.assertNotNull(page);
    }

    @Test
    void create() {
        ConfigCreateCommand command = ConfigCreateCommand.builder()
                .namespace("test_ns")
                .domain("www.matrixboot.com")
                .source("11.11.11.11")
                .build();
        configManagerService.configCreate(command);
        Optional<ConfigEntity> optional = repository.findByDomain("www.matrixboot.com");
        Assertions.assertTrue(optional.isPresent());
    }

}