package com.matrixboot.hub.controller.application.service;

import com.matrixboot.hub.controller.application.HeartbeatCommand;
import com.matrixboot.hub.controller.application.HeartbeatFactory;
import com.matrixboot.hub.controller.domain.repository.IHeartbeatRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * create in 2021/9/16 11:48 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Service
@AllArgsConstructor
public class NodeMonitorService {

    private final IHeartbeatRepository repository;

    @Transactional(rollbackFor = Exception.class)
    public void heartbeat(HeartbeatCommand command) {
        repository.save(HeartbeatFactory.from(command));
    }

}
