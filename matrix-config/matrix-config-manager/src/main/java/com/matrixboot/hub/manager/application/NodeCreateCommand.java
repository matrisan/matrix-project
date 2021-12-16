package com.matrixboot.hub.manager.application;

import com.matrixboot.hub.manager.infrastructure.validation.NodeVersion;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 节点创建命令
 * <p>
 * create in 2021/9/16 9:18 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NodeCreateCommand implements Serializable {

    private static final long serialVersionUID = 7507370343867062325L;

    /**
     * 名称
     */
    @NotBlank
    String name;

    /**
     * 容量
     */
    Integer capacity;

    /**
     * 版本
     */
    @NotBlank
    @NodeVersion
    String nodeVersion;


}
