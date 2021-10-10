package com.matrixboot.hub.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * create in 2021/9/28 12:23 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReturnVO<T> {

    T data;

    public static <T> ReturnVO<T> success(T data) {
        return ReturnVO.<T>builder()
                .data(data)
                .build();
    }

    public static <T> ReturnVO<T> success() {
        return ReturnVO.<T>builder().build();
    }

}
