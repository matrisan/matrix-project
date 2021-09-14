package com.matrixboot.manager.server.domain.service;

import com.matrixboot.manager.server.domain.entity.RegionNodeEntity;
import com.matrixboot.manager.server.domain.repository.IWebsiteRepository;
import com.matrixboot.manager.server.interfaces.dto.WebsiteInfoSyncCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * create in 2021/8/11 3:36 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Component
@AllArgsConstructor
public class WebsiteInfoSyncCommandPersistence implements IWebsiteInfoSyncCommand {

    private final IWebsiteRepository repository;

    @Override
    public void sync(@NotNull WebsiteInfoSyncCommand command, @NotNull List<RegionNodeEntity> nodeList) {
        repository.findById(command.getId()).ifPresent(entity -> nodeList.forEach(one -> one.addWebsiteInfo(entity)));
    }
}
