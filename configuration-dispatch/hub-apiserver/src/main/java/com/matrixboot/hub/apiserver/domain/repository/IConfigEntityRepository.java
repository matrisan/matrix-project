package com.matrixboot.hub.apiserver.domain.repository;

import com.matrixboot.hub.apiserver.domain.entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

/**
 * <p>
 * create in 2021/9/15 11:00 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

public interface IConfigEntityRepository extends JpaRepository<ConfigEntity, Long> {

    @Lock(LockModeType.WRITE)
    Optional<ConfigEntity> findFirstByStatus(int status);

}
