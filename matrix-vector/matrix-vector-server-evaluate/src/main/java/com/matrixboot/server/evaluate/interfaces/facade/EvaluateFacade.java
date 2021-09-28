package com.matrixboot.server.evaluate.interfaces.facade;

import com.matrixboot.common.ReturnVO;
import com.matrixboot.server.evaluate.application.EvaluateCommand;
import com.matrixboot.server.evaluate.application.EvaluateResult;
import com.matrixboot.server.evaluate.application.service.EvaluateService;
import com.matrixboot.server.evaluate.infrastructure.context.IEvaluateContext;
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

    private final IEvaluateContext context;

    @PostMapping("evaluate")
    public ReturnVO<EvaluateResult> evaluate(@RequestBody EvaluateCommand command) {
        return ReturnVO.success(service.evaluate(command));
    }

    public ReturnVO<EvaluateResult> recover(@RequestBody EvaluateCommand command) {
        return ReturnVO.success(context.getFinalResult());
    }

}
