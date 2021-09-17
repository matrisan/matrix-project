package com.matrixboot.hub.controller.application.task;

import com.matrixboot.hub.controller.domain.repository.IHeartbeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * create in 2021/9/17 9:41 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Service
public class HeartbeatHistoryService {

    @Resource
    private IHeartbeatRepository repository;

    public void deleteHistory() {
        repository.deleteByCreateDateBefore(LocalDateTime.now().minusDays(1));
    }

}
