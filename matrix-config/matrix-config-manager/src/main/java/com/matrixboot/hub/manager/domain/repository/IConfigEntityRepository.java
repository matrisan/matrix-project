package com.matrixboot.hub.manager.domain.repository;

import com.matrixboot.hub.manager.domain.IConfigView;
import com.matrixboot.hub.manager.domain.entity.MatrixConfigEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * <p>
 * create in 2021/9/15 11:00 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

public interface IConfigEntityRepository extends JpaRepository<MatrixConfigEntity, Long> {

    /**
     * 分页查找所有的配置信息
     *
     * @param pageable 分页信息
     * @param clz      投影字段
     * @return Page
     */
    Page<IConfigView> findAllBy(Pageable pageable, Class<IConfigView> clz);

    /**
     * 根据域名查找配置信息
     *
     * @param domain 域名信息
     * @return Optional
     */
    Optional<MatrixConfigEntity> findByDomain(String domain);

}
