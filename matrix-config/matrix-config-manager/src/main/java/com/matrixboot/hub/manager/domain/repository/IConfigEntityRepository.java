package com.matrixboot.hub.manager.domain.repository;

import com.matrixboot.hub.manager.domain.entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * create in 2021/9/15 11:00 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

public interface IConfigEntityRepository extends JpaRepository<ConfigEntity, Long> {

}
