package com.matrixboot.hub.monitor.application.service;

import com.matrixboot.hub.monitor.application.ConfigCreateCommand;
import com.matrixboot.hub.monitor.application.ConfigPublishCommand;
import com.matrixboot.hub.monitor.application.ConfigStatusFactory;
import com.matrixboot.hub.monitor.domain.entity.ConfigStatusEntity;
import com.matrixboot.hub.monitor.domain.repository.IConfigStatusRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;

/**
 * <p>
 * create in 2021/9/14 1:50 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Service
@Validated
@AllArgsConstructor
public class ConfigStatusService {

    private final IConfigStatusRepository configStatusRepository;

    public void configStatus(@Valid @NotNull ConfigCreateCommand command) {
        ConfigStatusEntity status = ConfigStatusFactory.from(command);
        status.createComplete();
        configStatusRepository.save(status);
    }

    @Transactional(rollbackFor = Exception.class)
    public void publish(@Valid @NotNull ConfigPublishCommand command) {
        Optional<ConfigStatusEntity> optional = configStatusRepository.findByDomain(command.getDomain());
        optional.ifPresent(ConfigStatusEntity::publishComplete);
    }


}
