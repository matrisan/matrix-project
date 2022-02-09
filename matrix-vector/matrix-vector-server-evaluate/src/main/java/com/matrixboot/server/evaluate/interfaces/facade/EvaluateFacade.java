package com.matrixboot.server.evaluate.interfaces.facade;

import com.matrixboot.common.ReturnVO;
import com.matrixboot.server.evaluate.application.EvaluateResult;
import com.matrixboot.server.evaluate.application.command.EventEvaluateCommand;
import com.matrixboot.server.evaluate.application.service.EvaluateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * create in 2021/9/28 12:20 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@RestController
@AllArgsConstructor
public class EvaluateFacade {

    private final EvaluateService service;

    /**
     * 请求风控系统
     *
     * @param command 事件请求
     * @return EvaluateResult
     */
    @PostMapping("evaluate")
    public ReturnVO<EvaluateResult> evaluate(@RequestBody EventEvaluateCommand command) {
        return ReturnVO.success(service.evaluate(command));
    }

}
