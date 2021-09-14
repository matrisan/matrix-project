package com.matrixboot.manager.server.infrastructure.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * <p>
 * create in 2021/8/11 4:24 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T extends Serializable> implements Serializable {

    Integer status;

    String message;

    T data;

    public static <T extends Serializable> ResultVO<T> success() {
        return ResultVO.<T>builder().status(1).build();
    }

}
