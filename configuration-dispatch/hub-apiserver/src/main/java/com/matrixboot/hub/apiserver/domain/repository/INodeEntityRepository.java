package com.matrixboot.hub.apiserver.domain.repository;


import com.matrixboot.hub.apiserver.domain.entity.NodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * create in 2021/9/15 10:03 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

public interface INodeEntityRepository extends JpaRepository<NodeEntity, Long> {


}
