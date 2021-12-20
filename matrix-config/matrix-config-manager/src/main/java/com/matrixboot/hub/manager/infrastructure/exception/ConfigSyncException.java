package com.matrixboot.hub.manager.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * create in 2021/9/17 3:41 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ConfigSyncException extends AbstractConfigSyncException {

    private static final long serialVersionUID = -6956026310775684070L;

    private Long nodeId;

    private Long configId;


}
