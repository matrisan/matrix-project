package com.matrixboot.hub.controller.domain.entity;

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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * <p>
 * create in 2021/9/16 11:57 下午
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
public class HeartbeatEntity extends BaseEntity {

    private static final long serialVersionUID = -7263659389370708788L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nodeIndo;

    Integer cpuCapacity;

    Integer memoryCapacity;

    Integer cpuUsage;

    Integer memoryUsage;

}
