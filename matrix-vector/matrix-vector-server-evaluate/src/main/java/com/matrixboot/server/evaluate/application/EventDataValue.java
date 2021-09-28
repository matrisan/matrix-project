package com.matrixboot.server.evaluate.application;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 事件传过来的数据
 * <p>
 * create in 2021/9/18 2:31 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Getter
@Setter
public class EventDataValue {

    private String phone;

    private String ip;

    private String mac;


}
