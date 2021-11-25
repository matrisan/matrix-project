package com.matrixboot.hub.manager.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.matrixboot.hub.common.entity.BaseEntity;
import com.matrixboot.hub.manager.domain.value.CapacityValue;
import com.matrixboot.hub.manager.domain.value.ExclusiveValue;
import com.matrixboot.hub.manager.domain.value.UsageValue;
import com.matrixboot.hub.manager.infrastructure.converter.CapacityConverter;
import com.matrixboot.hub.manager.infrastructure.converter.ExclusiveConverter;
import com.matrixboot.hub.manager.infrastructure.converter.UsageConverter;
import com.matrixboot.hub.manager.infrastructure.predicate.IPredicateStrategy;
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

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Map;

/**
 * create in 2021/9/14 5:53 下午
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
@Table(name = "NodeEntity")
public class NodeEntity extends BaseEntity {

    private static final long serialVersionUID = -8462673582623260919L;

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 节点名词
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(20) COMMENT '节点名词'")
    private String name;

    /**
     * 节点独享信息,一般时候用在某些
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(128) COMMENT '节点独享信息'")
    @Convert(converter = ExclusiveConverter.class)
    private ExclusiveValue exclusive;

    /**
     * 容量
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(128) COMMENT '容量'")
    @Convert(converter = CapacityConverter.class)
    private CapacityValue capacity;

    /**
     * 使用情况
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(128) COMMENT '使用情况'")
    @Convert(converter = UsageConverter.class)
    private UsageValue usage;

    /**
     * 节点的版本
     */
    @Column(name = "node_version", nullable = false, columnDefinition = "VARCHAR(16) COMMENT 'domain'")
    private String nodeVersion;

    /**
     * 配置列表
     */
    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "node", fetch = FetchType.LAZY)
    private List<ConfigEntity> configList;

    public boolean match(@NotNull ConfigEntity config, @NotNull Map<String, IPredicateStrategy> strategyMap) {
        NodeEntity node = this;
        return strategyMap.values().stream()
                .map(strategy -> strategy.match(node, config))
                .anyMatch(flag -> flag = Boolean.TRUE);
    }

    /**
     * 新增配置
     *
     * @param config 配置信息
     */
    public void addNewConfig(ConfigEntity config) {
        configList.add(config);
        this.usage.increase(config.getResource());
    }

    /**
     * 删除配置
     *
     * @param config 配置信息
     */
    public void deleteConfig(ConfigEntity config) {
        configList.remove(config);
        this.usage.reduce(config.getResource());
    }

    /**
     * 是否有剩余容量
     *
     * @return boolean
     */
    public boolean hasCapacity() {
        return usage.getUsage() >= capacity.getCapacity();
    }

}
