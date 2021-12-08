package com.matrixboot.hub.manager.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.matrixboot.hub.common.entity.BaseEntity;
import com.matrixboot.hub.manager.application.ConfigSyncCommand;
import com.matrixboot.hub.manager.application.ConfigSyncTypeEnum;
import com.matrixboot.hub.manager.domain.value.ResourceValue;
import com.matrixboot.hub.manager.infrastructure.converter.ResourceConverter;
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
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "ConfigEntity")
public class ConfigEntity extends BaseEntity {

    private static final long serialVersionUID = -5523364931796440110L;

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * 命名空间
     */
    @Column(nullable = false, columnDefinition = "CHAR(20) COMMENT 'namespace'")
    String namespace;

    /**
     * 域名
     */
    @Column(nullable = false, columnDefinition = "CHAR(20) COMMENT 'domain'")
    String domain;

    /**
     * 源站地址
     */
    @Column(nullable = false, columnDefinition = "CHAR(20) COMMENT 'source'")
    String source;

    /**
     * 状态
     */
    Integer status;

    /**
     * 节点选择器
     */
    @Column(nullable = false, columnDefinition = "CHAR(20) DEFAULT '' COMMENT 'selector'")
    String selector;

    /**
     * 消耗资源信息
     */
    @Convert(converter = ResourceConverter.class)
    @Column(name = "system_resource", nullable = false, columnDefinition = "VARCHAR(128) DEFAULT '' COMMENT '消耗资源信息'")
    ResourceValue systemResource;

    /**
     * 节点的 ID
     */
    @Column(name = "node_id", insertable = false, updatable = false)
    Long nodeId;

    /**
     * 节点详情
     */
    @ToString.Exclude
    @JsonBackReference
    @ManyToOne(targetEntity = NodeEntity.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id", referencedColumnName = "id")
    private NodeEntity node;

    /**
     * 新建事件
     *
     * @return ConfigSyncCommand
     */
    @DomainEvents
    ConfigSyncCommand domainEvents() {
        log.info("domainEvents");
        ConfigSyncCommand configSyncCommand = new ConfigSyncCommand();
        configSyncCommand.setId(this.id);
        configSyncCommand.setConfigSyncType(ConfigSyncTypeEnum.CREATE);
        return configSyncCommand;
    }

    /**
     * 事件回调
     */
    @AfterDomainEventPublication
    void callbackMethod() {
        log.info("AfterDomainEventPublication");
    }

}
