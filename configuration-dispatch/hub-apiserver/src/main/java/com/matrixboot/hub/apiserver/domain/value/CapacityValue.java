package com.matrixboot.hub.apiserver.domain.value;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * create in 2021/9/16 6:40 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Getter
@NoArgsConstructor
public class CapacityValue {

    private Integer capacity;

    public CapacityValue(Integer capacity) {
        this.capacity = capacity;
    }
}
