package com.matrixboot.hub.apiserver.application.service;

import com.matrixboot.hub.apiserver.application.ConfigSyncCommand;
import com.matrixboot.hub.apiserver.domain.repository.IConfigEntityRepository;
import com.matrixboot.hub.apiserver.domain.repository.INodeEntityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * create in 2021/9/15 11:09 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Service
@AllArgsConstructor
public class ConfigSchedulerService {

    private final IConfigEntityRepository configEntityRepository;

    private final INodeEntityRepository nodeEntityRepository;

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_UNCOMMITTED)
    public void sync(ConfigSyncCommand command) {
        configEntityRepository.findFirstByStatus(0).ifPresent(configEntity -> configEntity.setStatus(1));
    }


}
