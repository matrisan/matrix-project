package com.matrixboot.hub.manager.interfaces.facade;

import com.matrixboot.hub.manager.interfaces.vo.ReturnVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * create in 2021/12/16 10:56 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@RestController
public class MockFacade {

    @PostMapping("test_key")
    public ReturnVO<String> mock(@RequestBody String data) {
        log.info("mock - {}", data);
        return ReturnVO.success();
    }

}
