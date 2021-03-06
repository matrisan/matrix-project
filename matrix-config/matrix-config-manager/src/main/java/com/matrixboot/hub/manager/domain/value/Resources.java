package com.matrixboot.hub.manager.domain.value;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 资源值对象
 * <p>
 * create in 2021/9/16 10:42 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Resources implements Serializable {

    private static final long serialVersionUID = 7450480632603691196L;

    private Integer resource;

}
