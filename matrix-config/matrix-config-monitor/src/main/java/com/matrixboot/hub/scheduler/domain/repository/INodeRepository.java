package com.matrixboot.hub.scheduler.domain.repository;

import com.matrixboot.hub.scheduler.domain.entity.NodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * create in 2021/9/15 10:03 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

public interface INodeRepository extends JpaRepository<NodeEntity, Long> {


}
