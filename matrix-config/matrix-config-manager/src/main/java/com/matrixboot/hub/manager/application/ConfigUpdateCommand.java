package com.matrixboot.hub.manager.application;

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
 * <p>
 * create in 2021/12/10 11:36 AM
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
public class ConfigUpdateCommand implements Serializable {

    private static final long serialVersionUID = 5045128071689014992L;

    @NotNull
    Long id;

    @NotBlank
    String namespace;

    @NotBlank
    String domain;

    @NotBlank
    String source;

}
