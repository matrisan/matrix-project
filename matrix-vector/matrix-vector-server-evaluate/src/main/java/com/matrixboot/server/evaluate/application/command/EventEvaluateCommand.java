package com.matrixboot.server.evaluate.application.command;

import com.matrixboot.strategy.ScenarioMeta;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

/**
 * 接口请求数据
 *
 * <p>
 * create in 2021/9/18 2:25 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Getter
@Setter
public class EventEvaluateCommand {

    @Valid
    private ScenarioMeta scenarioMeta;

    @Valid
    private EventDataValue eventData;

    /**
     * 事件传过来的数据
     * <p>
     * create in 2021/9/18 2:31 下午
     *
     * @author shishaodong
     * @version 0.0.1
     */
    @Slf4j
    @Getter
    @Setter
    public static class EventDataValue {

        private String phone;

        private String ip;

        private String mac;

    }

}
