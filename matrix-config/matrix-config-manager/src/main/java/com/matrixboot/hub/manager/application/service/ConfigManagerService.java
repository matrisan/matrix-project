package com.matrixboot.hub.manager.application.service;

import com.matrixboot.hub.manager.application.ConfigCreateCommand;
import com.matrixboot.hub.manager.application.ConfigDeleteCommand;
import com.matrixboot.hub.manager.application.ConfigUpdateCommand;
import com.matrixboot.hub.manager.domain.IConfigView;
import com.matrixboot.hub.manager.domain.entity.MatrixConfigEntity;
import com.matrixboot.hub.manager.domain.repository.IConfigEntityRepository;
import com.matrixboot.hub.manager.infrastructure.event.ConfigDeleteEvent;
import com.matrixboot.hub.manager.infrastructure.exception.ConfigNotFoundException;
import com.matrixboot.hub.manager.infrastructure.transverter.MatrixConfigFactory;
import com.matrixboot.idempotent.annotation.Idempotent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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

    private final ApplicationContext context;

    /**
     * 分页查找所有的配置信息
     *
     * @param pageable 分页信息
     * @return Page
     */
    public Page<IConfigView> findAll(Pageable pageable) {
        return repository.findAllBy(pageable, IConfigView.class);
    }

    /**
     * 创建新的配置
     *
     * @param command ConfigCreateCommand
     */
    @Idempotent(value = "#command.domain", timeout = 10, unit = TimeUnit.SECONDS)
    public void configCreate(@Valid ConfigCreateCommand command) {
        MatrixConfigEntity save = repository.save(MatrixConfigFactory.create(command));
        log.info("配置已经保存 - {}", save);
    }

    /**
     * 创建新的配置
     *
     * @param command ConfigCreateCommand
     */
    public void configCreate(@NotNull @Size(min = 1) List<@Valid ConfigCreateCommand> command) {
        command.forEach(configCreateCommand -> {
            MatrixConfigEntity save = repository.save(MatrixConfigFactory.create(configCreateCommand));
            log.info("配置已经保存 - {}", save);
        });
    }

    /**
     * 更新配置,全量更新那种
     *
     * @param command 更新命令
     */
    @Transactional(rollbackFor = Exception.class)
    public void configUpdate(@Valid ConfigUpdateCommand command) {
        log.info("更新配置 - {}", command);
        Optional<MatrixConfigEntity> optional = repository.findById(command.getId());
        checkExist(optional, command);
        optional.ifPresent(config -> config.updateConfig(command, context));
    }

    /**
     * 删除配置
     *
     * @param command 删除命令
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteConfig(@NotNull @Valid ConfigDeleteCommand command) {
        Optional<MatrixConfigEntity> optional = repository.findById(command.getId());
        optional.ifPresent(config -> {
            repository.delete(config);
            context.publishEvent(new ConfigDeleteEvent(config));
        });
    }

    /**
     * 检查配置是否存在
     *
     * @param optional 配置项
     * @param command  更新命令
     */
    private void checkExist(@NotNull Optional<MatrixConfigEntity> optional, ConfigUpdateCommand command) {
        if (optional.isEmpty()) {
            throw new ConfigNotFoundException(command);
        }
    }
}
