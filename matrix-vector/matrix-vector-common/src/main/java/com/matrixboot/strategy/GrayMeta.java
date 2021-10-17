package com.matrixboot.strategy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 * <p>
 * create in 2021/10/17 12:07 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrayMeta {

    private Boolean enable;

    private Integer ratio;

}
