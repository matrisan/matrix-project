package com.matrixboot.hub.manager.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.matrixboot.hub.common.entity.BaseEntity;
import com.matrixboot.hub.manager.application.ConfigSyncCommand;
import com.matrixboot.hub.manager.application.ConfigSyncTypeEnum;
import com.matrixboot.hub.manager.application.ConfigUpdateCommand;
import com.matrixboot.hub.manager.domain.value.Resources;
import com.matrixboot.hub.manager.infrastructure.converter.ResourceConverter;
import com.matrixboot.hub.manager.infrastructure.event.ConfigUpdateEvent;
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
import org.springframework.context.ApplicationContext;
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
import javax.persistence.UniqueConstraint;

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
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_domain", columnNames = "domain")})
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
    Resources systemResource;

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


    public boolean haveResource() {
        return true;
    }

    public void updateConfig(@NotNull ConfigUpdateCommand command, @NotNull ApplicationContext applicationContext) {
        this.namespace = (command.getNamespace());
        this.domain = (command.getDomain());
        this.source = (command.getSource());
        applicationContext.publishEvent(new ConfigUpdateEvent(this));
    }


    /**
     * 新建事件
     *
     * @return ConfigSyncCommand
     */
    @DomainEvents
    public ConfigSyncCommand domainEvents() {
        log.info("ConfigEntity - domainEvents - {}", this);
        ConfigSyncCommand configSyncCommand = new ConfigSyncCommand();
        configSyncCommand.setId(this.id);
        configSyncCommand.setConfigSyncType(ConfigSyncTypeEnum.CREATE);
        return configSyncCommand;
    }

    /**
     * 事件回调
     */
    @AfterDomainEventPublication
    public void callbackMethod() {
        log.info("AfterDomainEventPublication");
    }

}
