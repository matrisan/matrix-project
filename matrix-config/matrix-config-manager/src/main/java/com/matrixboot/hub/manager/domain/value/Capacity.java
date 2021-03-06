package com.matrixboot.hub.manager.domain.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * <p>
 * create in 2021/9/16 6:40 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Capacity implements Serializable {

    private static final long serialVersionUID = 1314478756710027410L;

    /**
     * 容量
     */
    private Integer count;

}
