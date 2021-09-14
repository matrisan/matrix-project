package com.matrixboot.manager.server.domain.service;


import com.matrixboot.manager.server.domain.entity.RegionNodeEntity;

import java.util.List;

/**
 * <p>
 * create in 2021/8/11 3:09 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@FunctionalInterface
public interface IRegionPickStrategy {

    List<RegionNodeEntity> pickSomeNodes();

}


