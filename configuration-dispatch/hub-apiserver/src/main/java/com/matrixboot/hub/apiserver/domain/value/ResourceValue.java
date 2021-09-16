package com.matrixboot.hub.apiserver.domain.value;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 * <p>
 * create in 2021/9/16 10:42 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Getter
@NoArgsConstructor
public class ResourceValue {

    private Integer resource;

    public ResourceValue(Integer resource) {
        this.resource = resource;
    }
}
