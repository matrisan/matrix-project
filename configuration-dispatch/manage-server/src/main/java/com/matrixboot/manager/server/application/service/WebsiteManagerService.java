package com.matrixboot.manager.server.application.service;


import com.matrixboot.manager.server.application.WebsiteFactory;
import com.matrixboot.manager.server.domain.entity.WebsiteInfoEntity;
import com.matrixboot.manager.server.domain.repository.IWebsiteRepository;
import com.matrixboot.manager.server.domain.service.IRecordCheckStrategy;
import com.matrixboot.manager.server.interfaces.dto.WebsiteAddCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * create in 2021/8/11 2:47 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@AllArgsConstructor
@Service
@Validated
public class WebsiteManagerService {

    private final IRecordCheckStrategy service;

    private final IWebsiteRepository repository;

    public void addNewWebsite(@Valid WebsiteAddCommand command) {
        WebsiteInfoEntity from = WebsiteFactory.from(command);
        if (from.isRecorded(service)) {
            repository.save(from);
        }
    }
}
