package com.matrixboot.hub.manager.application.service;

import com.matrixboot.hub.manager.application.ConfigCreateCommand;
import com.matrixboot.hub.manager.application.ConfigDeleteCommand;
import com.matrixboot.hub.manager.application.ConfigSyncCommand;
import com.matrixboot.hub.manager.application.ConfigSyncTypeEnum;
import com.matrixboot.hub.manager.application.ConfigUpdateCommand;
import com.matrixboot.hub.manager.domain.IConfigView;
import com.matrixboot.hub.manager.domain.entity.MatrixConfigEntity;
import com.matrixboot.hub.manager.domain.repository.IConfigEntityRepository;
import com.matrixboot.hub.manager.infrastructure.event.ConfigDeleteEvent;
import com.matrixboot.hub.manager.infrastructure.event.ConfigUpdateEvent;
import com.matrixboot.hub.manager.infrastructure.event.IEventPublisher;
import com.matrixboot.hub.manager.infrastructure.exception.ConfigNotFoundException;
import com.matrixboot.hub.manager.infrastructure.transverter.MatrixConfigFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.matrixboot.hub.manager.infrastructure.event.MqTopicEnum.CONFIG_CREATE;
import static com.matrixboot.hub.manager.infrastructure.event.MqTopicEnum.CONFIG_DELETE;
import static com.matrixboot.hub.manager.infrastructure.event.MqTopicEnum.CONFIG_UPDATE;


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

    private final IEventPublisher eventPublisher;

    private final IConfigEntityRepository repository;

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
    public void configCreate(@Valid ConfigCreateCommand command) {
        MatrixConfigEntity save = repository.save(MatrixConfigFactory.create(command));
        log.info("配置已经保存 - {}", save);
        eventPublisher.publish(new ConfigSyncCommand(save.getId(), ConfigSyncTypeEnum.CREATE), CONFIG_CREATE.name());
    }

    /**
     * 创建新的配置
     *
     * @param command ConfigCreateCommand
     */
    public void configCreate(@NotNull @Size(min = 1) List<@Valid ConfigCreateCommand> command) {
        List<MatrixConfigEntity> list = command.stream().map(MatrixConfigFactory::create).collect(Collectors.toList());
        List<MatrixConfigEntity> entities = repository.saveAll(list);
        log.info("配置已经保存 - {}", list);
        eventPublisher.publish(convertToCommand(entities), CONFIG_CREATE.name());
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
        optional.ifPresent(config -> {
            config.updateConfig(command);
            eventPublisher.publish(new ConfigUpdateEvent(config), CONFIG_UPDATE.name());
        });
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
            eventPublisher.publish(new ConfigDeleteEvent(config), CONFIG_DELETE.name());
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

    /**
     * 转换成命令
     *
     * @param entities MatrixConfigEntity
     * @return List
     */
    @NotNull
    private List<ConfigSyncCommand> convertToCommand(@NotNull List<MatrixConfigEntity> entities) {
        return entities.stream()
                .map(one -> new ConfigSyncCommand(one.getId(), ConfigSyncTypeEnum.CREATE))
                .collect(Collectors.toList());
    }
}
