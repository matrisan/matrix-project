package com.matrixboot.hub.manager.interfaces.facade;

import com.matrixboot.hub.manager.application.NodeCreateCommand;
import com.matrixboot.hub.manager.application.service.NodeManagerService;
import com.matrixboot.hub.manager.interfaces.vo.ReturnVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 节点管理接口
 * <p>
 * create in 2021/12/16 8:01 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@RestController
@AllArgsConstructor
public class NodeManagerFacade {

    @Resource
    private NodeManagerService nodeManagerService;

    @PostMapping("node")
    public ReturnVO<String> createNode(@RequestBody NodeCreateCommand command) {
        nodeManagerService.createNode(command);
        return ReturnVO.success();
    }

}
