package com.matrixboot.hub.apiserver.domain.entity;

import com.matrixboot.hub.apiserver.application.ConfigSyncCommand;
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
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * TODO
 * <p>
 * create in 2021/9/15 10:57 下午
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
public class ConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, columnDefinition = "CHAR(20) COMMENT 'namespace'")
    String namespace;

    @Column(nullable = false, columnDefinition = "CHAR(20) COMMENT 'domain'")
    String domain;

    @Column(nullable = false, columnDefinition = "CHAR(20) COMMENT 'source'")
    String source;

    Integer status;

    @DomainEvents
    ConfigSyncCommand domainEvents() {
        log.info("domainEvents");
        return new ConfigSyncCommand();
    }

    @AfterDomainEventPublication
    void callbackMethod() {
        log.info("AfterDomainEventPublication");
    }


}
