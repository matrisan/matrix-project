package com.matrixboot.hub.monitor.application.service;

import com.matrixboot.hub.monitor.application.ConfigCreateCommand;
import com.matrixboot.hub.monitor.domain.entity.ConfigStatusEntity;
import com.matrixboot.hub.monitor.domain.repository.IConfigStatusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * TODO
 * <p>
 * create in 2021/12/9 10:44 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@SpringBootTest
@ActiveProfiles("junit")
class ConfigStatusServiceTest {

    @Resource
    ConfigStatusService statusService;

    @Resource
    IConfigStatusRepository configStatusRepository;

    private static final String DOMAIN = "www.matrixboot.com";

    @BeforeEach
    void init() {
        configStatusRepository.deleteAll();
    }

    @Test
    @Rollback
    void configStatus() {
        statusService.configStatus(new ConfigCreateCommand(DOMAIN));
        Optional<ConfigStatusEntity> optional = configStatusRepository.findByDomain(DOMAIN);
        Assertions.assertTrue(optional.isPresent());
    }

    @Test
    @Rollback
    void publish() {
        statusService.configStatus(new ConfigCreateCommand(DOMAIN));
        Optional<ConfigStatusEntity> optional = configStatusRepository.findByDomain(DOMAIN);
        optional.ifPresent(ConfigStatusEntity::publishComplete);
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertTrue(optional.get().getPublished());
    }
}