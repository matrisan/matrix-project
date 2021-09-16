package com.matrixboot.hub.scheduler.application.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * create in 2021/9/15 10:02 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Getter
@Setter
public class UpdateCommand {

    private Long id;

    private String ip;

    private String name;

    private String note;

}
