package com.matrixboot.hub.manager.application.service;

import com.matrixboot.hub.manager.application.ConfigCreateCommand;
import com.matrixboot.hub.manager.domain.IConfigView;
import com.matrixboot.hub.manager.domain.repository.IConfigEntityRepository;
import com.matrixboot.hub.manager.infrastructure.transverter.ConfigFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * create in 2021/9/15 10:47 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Service
@Validated
@AllArgsConstructor
public class ConfigManagerService {

    private final IConfigEntityRepository repository;

    public Page<IConfigView> findAll(Pageable pageable) {
        return repository.findAllBy(pageable, IConfigView.class);
    }


    /**
     * 创建新的配置
     *
     * @param command ConfigCreateCommand
     */
    public void create(@Valid ConfigCreateCommand command) {
        repository.save(ConfigFactory.create(command));
    }


}
