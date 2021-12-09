package com.matrixboot.hub.monitor.domain.entity;

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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

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
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_domain", columnNames = "domain")})
public class ConfigStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, columnDefinition = "CHAR(20) COMMENT 'domainName'")
    String domain;

    @Column(columnDefinition = "INT(1) DEFAULT 0 COMMENT 'created'")
    Boolean created;

    @Column(columnDefinition = "INT(1) DEFAULT 0 COMMENT 'published'")
    Boolean published;

//    @Column(nullable = false, columnDefinition = "CHAR(20) COMMENT 'domainName'")
//    List<String> sites;

    @Column(columnDefinition = "CHAR(20) COMMENT 'domainName'")
    String note;

    @Version
    Long version;

    public void createComplete() {
        this.created = true;
    }

    public void publishComplete() {
        this.published = true;
    }

    public void dnsDetected() {

    }

    public void sourceStationDetected() {

    }


}
