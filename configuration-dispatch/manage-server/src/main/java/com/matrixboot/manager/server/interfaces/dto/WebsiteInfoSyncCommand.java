package com.matrixboot.manager.server.interfaces.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * create in 2021/8/11 3:04 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Data
public class WebsiteInfoSyncCommand {

    @NotNull
    Long id;

}
