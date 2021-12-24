package com.matrixboot.hub.manager.interfaces.facade;

import com.matrixboot.hub.manager.application.ConfigSyncCommand;
import com.matrixboot.hub.manager.application.service.ConfigScheduleService;
import com.matrixboot.hub.manager.infrastructure.event.ConfigCreateEvent;
import com.matrixboot.hub.manager.interfaces.vo.ReturnVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * create in 2021/10/9 10:29 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@RestController
@AllArgsConstructor
public class ConfigSyncFacade {

    private final ConfigScheduleService service;

    @PostMapping("sync")
    public ReturnVO<String> restSyncConfig(@RequestBody ConfigSyncCommand command) {
        service.syncConfig(command);
        return ReturnVO.success();
    }

    @EventListener
    public void syncConfig(@NotNull ConfigCreateEvent event) {
        service.syncConfig((ConfigSyncCommand) event.getCommand());
    }

}
