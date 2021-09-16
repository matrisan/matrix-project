package com.matrixboot.hub.apiserver.domain.entity;

import com.matrixboot.hub.apiserver.domain.value.CapacityValue;
import com.matrixboot.hub.apiserver.domain.value.ExclusiveValue;
import com.matrixboot.hub.apiserver.domain.value.InventoryValue;
import com.matrixboot.hub.apiserver.infrastructure.IPredicateStrategy;
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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;
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

    @Transient
    ExclusiveValue exclusive;

    @Transient
    CapacityValue capacity;

    @Transient
    InventoryValue inventory;

    @Version
    Long version;


    public boolean match(ConfigEntity entity, @NotNull Map<String, IPredicateStrategy> strategyMap) {
        NodeEntity node = this;
        return strategyMap.values().stream()
                .map(iPredicateStrategy -> iPredicateStrategy.match(node, entity))
                .anyMatch(aBoolean -> aBoolean = Boolean.TRUE);
    }

    public void addNewConfig(ConfigEntity entity) {

    }

}
