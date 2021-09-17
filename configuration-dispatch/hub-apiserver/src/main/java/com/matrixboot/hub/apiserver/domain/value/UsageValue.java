package com.matrixboot.hub.apiserver.domain.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * <p>
 * create in 2021/9/16 6:41 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsageValue {

    @Getter
    private Integer usage;

    public void increase(@NotNull ResourceValue resource) {
        this.usage = this.usage + resource.getResource();
    }

    public void reduce(@NotNull ResourceValue resource) {
        this.usage = this.usage - resource.getResource();
    }

}
