package com.matrixboot.manager.server.domain.service;


import com.matrixboot.manager.server.domain.entity.RegionNodeEntity;
import com.matrixboot.manager.server.interfaces.dto.WebsiteInfoSyncCommand;

import java.util.List;

/**
 * <p>
 * create in 2021/8/11 3:21 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

public interface IWebsiteInfoSyncCommand {

    void sync(WebsiteInfoSyncCommand command, List<RegionNodeEntity> nodeList);

}
