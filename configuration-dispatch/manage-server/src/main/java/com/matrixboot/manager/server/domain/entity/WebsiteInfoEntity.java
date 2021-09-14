package com.matrixboot.manager.server.domain.entity;


import com.matrixboot.manager.server.domain.service.IRecordCheckStrategy;
import com.matrixboot.manager.server.interfaces.dto.WebsiteInfoSyncCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * <p>
 * create in 2021/8/11 2:41 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicInsert
@DynamicUpdate
@Entity
public class WebsiteInfoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, columnDefinition = "CHAR(20) COMMENT 'domainName'")
    String domainName;

    /**
     * 检查是否备案
     *
     * @return boolean
     */
    public boolean isRecorded(@NotNull IRecordCheckStrategy service) {
        return service.isRecord(this.domainName);
    }

    @DomainEvents
    WebsiteInfoSyncCommand domainEvents() {
        log.info("domainEvents");
        return new WebsiteInfoSyncCommand();
    }

    @AfterDomainEventPublication
    void callbackMethod() {
        log.info("AfterDomainEventPublication");
    }

}
