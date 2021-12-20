package com.matrixboot.hub.manager.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.matrixboot.hub.common.entity.BaseEntity;
import com.matrixboot.hub.manager.domain.value.Capacity;
import com.matrixboot.hub.manager.domain.value.Exclusive;
import com.matrixboot.hub.manager.domain.value.Usage;
import com.matrixboot.hub.manager.infrastructure.converter.CapacityConverter;
import com.matrixboot.hub.manager.infrastructure.converter.ExclusiveConverter;
import com.matrixboot.hub.manager.infrastructure.converter.UsageConverter;
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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

import static javax.persistence.ConstraintMode.NO_CONSTRAINT;

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
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_name", columnNames = "name")})
public class MatrixNodeEntity extends BaseEntity {

    private static final long serialVersionUID = -8462673582623260919L;

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * 节点名词
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(20) COMMENT '节点名词'")
    String name;

    /**
     * 状态
     */
    @Column(columnDefinition = "INT(1) DEFAULT 1 COMMENT '状态'")
    Integer status;

    /**
     * 节点独享信息,一般时候用在某些
     */
    @Column(columnDefinition = "VARCHAR(128) COMMENT '节点独享信息'")
    @Convert(converter = ExclusiveConverter.class)
    Exclusive exclusive;

    /**
     * 容量
     */
    @Column(columnDefinition = "VARCHAR(128) COMMENT '容量'")
    @Convert(converter = CapacityConverter.class)
    Capacity capacity;

    /**
     * 使用情况
     */
    @Column(columnDefinition = "VARCHAR(128) COMMENT '使用情况'")
    @Convert(converter = UsageConverter.class)
    Usage resourceUsage;

    /**
     * 节点的版本
     */
    @Column(name = "node_version", nullable = false, columnDefinition = "VARCHAR(16) COMMENT 'domain'")
    String nodeVersion;

    /**
     * 配置列表
     */
    @ToString.Exclude
    @JsonManagedReference
    @ManyToMany(targetEntity = MatrixConfigEntity.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "matrix_mid_config_node",
            joinColumns = @JoinColumn(name = "mid_node_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "none", value = NO_CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(name = "mid_config_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "none", value = NO_CONSTRAINT))
    )
    private List<MatrixConfigEntity> configs;

    /**
     * 新增配置
     *
     * @param config 配置信息
     */
    public void addNewConfig(MatrixConfigEntity config) {
        configs.add(config);
        this.resourceUsage.increase(config.getResources());
    }

    /**
     * 删除配置
     *
     * @param config 配置信息
     */
    public void deleteConfig(MatrixConfigEntity config) {
        configs.remove(config);
        this.resourceUsage.reduce(config.getResources());
    }

    /**
     * 是否有剩余容量
     *
     * @return boolean
     */
    public boolean hasCapacity() {
        return resourceUsage.getAmount() >= capacity.getCount();
    }

}
