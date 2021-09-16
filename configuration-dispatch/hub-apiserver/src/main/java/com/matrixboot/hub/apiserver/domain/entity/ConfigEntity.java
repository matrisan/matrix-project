package com.matrixboot.hub.apiserver.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.matrixboot.hub.apiserver.application.ConfigSyncCommand;
import com.matrixboot.hub.apiserver.domain.value.ResourceValue;
import com.matrixboot.hub.common.entity.BaseEntity;
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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * <p>
 * create in 2021/9/15 10:57 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@SuppressWarnings("unused")
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
public class ConfigEntity extends BaseEntity {

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

    @Column(nullable = false, columnDefinition = "CHAR(20) COMMENT 'selector'")
    String selector;

    ResourceValue resource;

    @Column(name = "node_id", insertable = false, updatable = false)
    Long nodeId;

    @ToString.Exclude
    @ManyToOne(targetEntity = NodeEntity.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id", referencedColumnName = "id")
    @JsonBackReference
    private NodeEntity node;

    /**
     * 新建事件
     *
     * @return ConfigSyncCommand
     */
    @DomainEvents
    ConfigSyncCommand domainEvents() {
        log.info("domainEvents");
        return new ConfigSyncCommand();
    }

    /**
     * 事件回调
     */
    @AfterDomainEventPublication
    void callbackMethod() {
        log.info("AfterDomainEventPublication");
    }

}
