package com.matrixboot.hub.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author shishaodong
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 2564431570747861624L;

    /**
     * 备注信息
     */
    @Column(nullable = false, columnDefinition = "CHAR(100) COMMENT 'note'")
    private String note;

    /**
     * 是否为预置
     */
    @JsonIgnore
    @Column(columnDefinition = "INT(1) DEFAULT 0 COMMENT '是否为预置'")
    private Boolean preset;

    /**
     * 是都逻辑删除
     */
    @Column(columnDefinition = "INT(1) DEFAULT 0 COMMENT '改记录是否删除'")
    private Boolean deleted;

    /**
     * 创建日期
     */
    @CreatedDate
    @Column(name = "create_date", columnDefinition = "DATETIME COMMENT '创建时间'")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDate;

    /**
     * 最后更新日期
     */
    @LastModifiedDate
    @Column(name = "last_modified_date", columnDefinition = "DATETIME COMMENT '最后更新时间'")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastModifiedDate;

    /**
     * 创建人
     */
    @CreatedBy
    @Column(name = "create_by", columnDefinition = "VARCHAR(100) COMMENT '创建人'")
    private String createBy;

    /**
     * 最后更新人
     */
    @LastModifiedBy
    @Column(name = "last_modified_by", columnDefinition = "VARCHAR(100) COMMENT '最后更新人'")
    private String lastModifiedBy;


    @Version
    Long version;

}

