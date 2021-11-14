package com.matrixboot.server.compute.domain.entity;

import com.matrixboot.strategy.ScenarioMetaEntity;
import lombok.Data;

/**
 * TODO
 * <p>
 * create in 2021/11/14 10:55 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Data
public class ComputeEntity extends ScenarioMetaEntity {

    private String code;

    private String scriptSha;

    private String[] args;

}
