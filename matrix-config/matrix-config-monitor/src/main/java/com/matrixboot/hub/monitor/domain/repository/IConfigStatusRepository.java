package com.matrixboot.hub.monitor.domain.repository;

import com.matrixboot.hub.monitor.domain.entity.ConfigStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * <p>
 * create in 2021/9/15 10:03 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

public interface IConfigStatusRepository extends JpaRepository<ConfigStatusEntity, Long> {

    Optional<ConfigStatusEntity> findByDomain(String domain);

}
