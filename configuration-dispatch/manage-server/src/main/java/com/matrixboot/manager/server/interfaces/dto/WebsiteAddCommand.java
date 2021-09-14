package com.matrixboot.manager.server.interfaces.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * create in 2021/8/11 2:49 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Data
public class WebsiteAddCommand {

    @NotBlank
    String domainName;

}
