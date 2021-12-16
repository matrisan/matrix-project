package com.matrixboot.hub.manager.application;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

/**
 * 节点更新命令
 * create in 2021/9/16 9:21 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NodeUpdateCommand extends NodeCreateCommand {

    private static final long serialVersionUID = -6732527728412939294L;

    @NotNull
    Long id;


}
