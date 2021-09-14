package com.matrixboot.manager.server.interfaces.facade;


import com.matrixboot.manager.server.application.service.WebsiteManagerService;
import com.matrixboot.manager.server.infrastructure.common.ResultVO;
import com.matrixboot.manager.server.interfaces.dto.WebsiteAddCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * create in 2021/8/11 3:38 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@RestController
@AllArgsConstructor
public class WebsiteManagerFacade {

    private final WebsiteManagerService websiteManagerService;

    @PostMapping("website")
    public ResultVO<String> manager(@RequestBody WebsiteAddCommand command) {
        websiteManagerService.addNewWebsite(command);
        return ResultVO.success();
    }

}
