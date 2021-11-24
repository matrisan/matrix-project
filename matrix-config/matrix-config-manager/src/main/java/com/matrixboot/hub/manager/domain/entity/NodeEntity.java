package com.matrixboot.hub.manager.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.matrixboot.hub.manager.domain.value.CapacityValue;
import com.matrixboot.hub.manager.domain.value.ExclusiveValue;
import com.matrixboot.hub.manager.domain.value.UsageValue;
import com.matrixboot.hub.manager.infrastructure.converter.CapacityConverter;
import com.matrixboot.hub.manager.infrastructure.converter.ExclusiveConverter;
import com.matrixboot.hub.manager.infrastructure.converter.UsageConverter;
import com.matrixboot.hub.manager.infrastructure.predicate.IPredicateStrategy;
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
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class NodeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, columnDefinition = "CHAR(20) COMMENT 'name'")
    String name;

    @Column(nullable = false, columnDefinition = "CHAR(20) COMMENT 'sites'")
    List<String> sites;

    /**
     * 节点独享信息,一般时候用在某些
     */
    @Convert(converter = ExclusiveConverter.class)
    ExclusiveValue exclusive;

    /**
     * 容量
     */
    @Convert(converter = CapacityConverter.class)
    CapacityValue capacity;

    /**
     * 使用情况
     */
    @Convert(converter = UsageConverter.class)
    UsageValue usage;

    String nodeVersion;


    /**
     * 配置列表
     */
    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "node", fetch = FetchType.LAZY)
    List<ConfigEntity> configList;

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
