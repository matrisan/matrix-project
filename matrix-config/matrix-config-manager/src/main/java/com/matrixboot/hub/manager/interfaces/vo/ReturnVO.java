package com.matrixboot.hub.manager.interfaces.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

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
public class ReturnVO<T> implements Serializable {

    private static final long serialVersionUID = -1095916834410197538L;

    private Integer code;

    private String message;

    private T data;

    public static <T> ReturnVO<T> success() {
        return ReturnVO.<T>builder().code(200).message("执行成功").build();
    }

    public static <T> ReturnVO<T> success(String message) {
        return ReturnVO.<T>builder().code(200).message(message).build();
    }

    public static <T> ReturnVO<T> success(String message, T data) {
        return ReturnVO.<T>builder().code(200).message(message).data(data).build();
    }

    public static <T> ReturnVO<T> success(T data) {
        return ReturnVO.<T>builder().code(200).message("执行成功").data(data).build();
    }

    public static <T> ReturnVO<T> failure() {
        return ReturnVO.<T>builder().code(500).message("执行失败").build();
    }

    public static <T> ReturnVO<T> failure(String message) {
        return ReturnVO.<T>builder().code(500).message(message).build();
    }

    public static <T> ReturnVO<T> failure(String message, T data) {
        return ReturnVO.<T>builder().code(500).message(message).data(data).build();
    }

    public static <T> ReturnVO<T> failure(T data) {
        return ReturnVO.<T>builder().code(500).message("执行失败").data(data).build();
    }

}
