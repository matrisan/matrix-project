package com.matrixboot.hub.manager.application;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 配置创建命令
 * create in 2021/9/15 10:58 下午
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
public class ConfigCreateCommand implements Serializable {

    private static final long serialVersionUID = -3758291503242328754L;

    @NotBlank
    @ExcelProperty("namespace")
    String namespace;

    @NotBlank
    @ExcelProperty("domain")
    String domain;

    @NotBlank
    @ExcelProperty("source")
    String source;

    @NotNull
    @ExcelProperty("resources")
    Integer resources;

}
