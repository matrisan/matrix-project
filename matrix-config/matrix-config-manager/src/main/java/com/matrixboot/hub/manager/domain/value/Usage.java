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
public class Usage implements Serializable {

    private static final long serialVersionUID = -1612988031094806630L;

    /**
     * 使用量
     */
    private Integer amount;

    public void increase(@NotNull Resources resource) {
        this.amount = this.amount + resource.getResource();
    }

    public void reduce(@NotNull Resources resource) {
        this.amount = this.amount - resource.getResource();
    }

}
