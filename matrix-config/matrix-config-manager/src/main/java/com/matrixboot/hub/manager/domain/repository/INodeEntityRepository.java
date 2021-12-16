package com.matrixboot.hub.manager.domain.repository;


import com.matrixboot.hub.manager.domain.entity.MatrixNodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * create in 2021/9/15 10:03 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

public interface INodeEntityRepository extends JpaRepository<MatrixNodeEntity, Long> {

}
