package com.matrixboot.hub.manager.domain.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * <p>
 * create in 2021/9/16 7:51 δΈε
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Exclusive implements Serializable {

    private static final long serialVersionUID = -7136206573369430387L;

    /**
     * ζι€
     */
    @Getter
    private Boolean condition;

}
