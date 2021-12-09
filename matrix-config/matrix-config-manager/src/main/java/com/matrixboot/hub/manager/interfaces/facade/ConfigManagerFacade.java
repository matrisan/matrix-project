package com.matrixboot.hub.manager.interfaces.facade;

import com.matrixboot.hub.manager.application.ConfigCreateCommand;
import com.matrixboot.hub.manager.application.service.ConfigManagerService;
import com.matrixboot.hub.manager.domain.IConfigView;
import com.matrixboot.hub.manager.interfaces.vo.ReturnVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * create in 2021/10/9 10:39 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@RestController
@AllArgsConstructor
public class ConfigManagerFacade {

    private final ConfigManagerService service;

    @GetMapping("configs")
    public Page<IConfigView> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @PostMapping("config")
    public ReturnVO<String> config(@RequestBody ConfigCreateCommand command) {
        service.create(command);
        return ReturnVO.success();
    }

}
