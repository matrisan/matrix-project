package com.matrixboot.manager.server.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * create in 2021/9/14 1:12 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Component
public class ConfigurationScheduler {

    @Resource
    private Map<String, AbstractScheduler> schedulerMap;

    @Resource
    private AbstractScheduler scheduler;

    public AbstractScheduler getScheduler() {
//        schedulerMap.getOrDefault()
        return null;
    }


}
