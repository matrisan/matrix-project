package com.matrixboot.server.evaluate.infrastructure.generator;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * TODO
 * <p>
 * create in 2021/9/28 12:37 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Component
public class EventIdGeneratorRandom implements IEventIdGenerator {

    @Override
    public String getId() {
        return RandomStringUtils.randomAlphabetic(10);
    }
}
