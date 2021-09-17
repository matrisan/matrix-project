package com.matrixboot.hub.controller.domain.repository;

import com.matrixboot.hub.controller.domain.entity.HeartbeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

/**
 * <p>
 * create in 2021/9/16 11:58 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

public interface IHeartbeatRepository extends JpaRepository<HeartbeatEntity, Long> {

    /**
     * 删除过期的数据
     *
     * @param beforeDate 过期的时间
     * @return int
     */
    int deleteByCreateDateBefore(LocalDateTime beforeDate);

}
