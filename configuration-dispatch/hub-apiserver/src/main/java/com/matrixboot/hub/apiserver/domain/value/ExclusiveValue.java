package com.matrixboot.hub.apiserver.domain.value;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * create in 2021/9/16 7:51 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
public class ExclusiveValue {

    @Getter
    private Boolean exclusive;

    public ExclusiveValue(Boolean exclusive) {
        this.exclusive = exclusive;
    }
}
