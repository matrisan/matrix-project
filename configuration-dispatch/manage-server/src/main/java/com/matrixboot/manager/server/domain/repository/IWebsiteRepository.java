package com.matrixboot.manager.server.domain.repository;


import com.matrixboot.manager.server.domain.entity.WebsiteInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * create in 2021/8/11 2:53 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

public interface IWebsiteRepository extends JpaRepository<WebsiteInfoEntity, Long> {

}
