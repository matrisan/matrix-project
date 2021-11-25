package com.matrixboot.hub.manager.domain.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * <p>
 * create in 2021/9/16 6:41 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsageValue implements Serializable {

    private static final long serialVersionUID = -1612988031094806630L;

    private Integer usage;

    public void increase(@NotNull ResourceValue resource) {
        this.usage = this.usage + resource.getResource();
    }

    public void reduce(@NotNull ResourceValue resource) {
        this.usage = this.usage - resource.getResource();
    }

}
