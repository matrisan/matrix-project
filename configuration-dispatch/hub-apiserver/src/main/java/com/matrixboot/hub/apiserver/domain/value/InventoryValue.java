package com.matrixboot.hub.apiserver.domain.value;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
public class InventoryValue {

    private Integer inventory;

    public InventoryValue(Integer inventory) {
        this.inventory = inventory;
    }
}
