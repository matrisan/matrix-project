package com.matrixboot.hub.apiserver.application.service;

import com.matrixboot.hub.apiserver.application.ConfigCreateCommand;
import com.matrixboot.hub.apiserver.application.ConfigFactory;
import com.matrixboot.hub.apiserver.domain.repository.IConfigEntityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * create in 2021/9/15 10:47 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Service
@AllArgsConstructor
public class ConfigManagerService {

    private final IConfigEntityRepository repository;

    public void create(ConfigCreateCommand command) {
        repository.save(ConfigFactory.create(command));
    }


}
