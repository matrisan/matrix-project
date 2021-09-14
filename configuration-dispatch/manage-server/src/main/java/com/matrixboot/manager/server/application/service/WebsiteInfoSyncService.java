package com.matrixboot.manager.server.application.service;


import com.matrixboot.manager.server.domain.service.IRegionPickStrategy;
import com.matrixboot.manager.server.domain.service.IWebsiteInfoSyncCommand;
import com.matrixboot.manager.server.interfaces.dto.WebsiteInfoSyncCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * create in 2021/8/11 3:04 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Service
@Validated
@AllArgsConstructor
public class WebsiteInfoSyncService {

    private final IRegionPickStrategy service;

    private final Map<String, IWebsiteInfoSyncCommand> map;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void websiteInfoSync(@Valid WebsiteInfoSyncCommand command) {
        map.values().forEach(sync -> sync.sync(command, service.pickSomeNodes()));
    }
}
