package com.matrixboot.hub.manager.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.matrixboot.hub.common.entity.BaseEntity;
import com.matrixboot.hub.manager.application.ConfigSyncCommand;
import com.matrixboot.hub.manager.application.ConfigSyncTypeEnum;
import com.matrixboot.hub.manager.application.ConfigUpdateCommand;
import com.matrixboot.hub.manager.domain.value.Resources;
import com.matrixboot.hub.manager.infrastructure.converter.ResourceConverter;
import com.matrixboot.hub.manager.infrastructure.event.ConfigUpdateEvent;
import com.matrixboot.hub.manager.infrastructure.inspect.IConfigInspect;
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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

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
public class MatrixConfigEntity extends BaseEntity {

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
     * 域名
     */
    @Column(columnDefinition = "CHAR(20) COMMENT 'domain'")
    String port;

    /**
     * 源站地址
     */
    @Column(nullable = false, columnDefinition = "CHAR(20) COMMENT 'source'")
    String source;

    /**
     * 状态
     */
    @Column(columnDefinition = "INT(1) DEFAULT 0 COMMENT '状态'")
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
    Resources resources;

    /**
     * 节点详情
     */
    @ToString.Exclude
    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.DETACH}, mappedBy = "configs")
    private List<MatrixNodeEntity> nodes;


    /**
     * 检查配置是否符合要求
     *
     * @param inspectList 配置检查项
     */
    public void inspect(@NotNull List<IConfigInspect> inspectList) {
        MatrixConfigEntity config = this;
        inspectList.forEach(iConfigInspect -> iConfigInspect.inspect(config));
    }

    public void disable() {
        this.status = 0;
    }

    public void enable() {
        this.status = 1;
    }


    /**
     * 更新配置
     *
     * @param command            更新命令
     * @param applicationContext ApplicationContext
     */
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
    ConfigSyncCommand domainEvents() {
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
    void callbackMethod() {
        log.info("AfterDomainEventPublication");
    }

}
