package com.matrixboot.strategy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

/**
 * 场景的元数据,用来标识是哪个场景
 *
 * <p>
 * create in 2021/10/16 11:32 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScenarioMeta implements Serializable {

    private static final long serialVersionUID = -7072700193350972587L;

    @NotBlank
    protected String projectId;

    @NotBlank
    protected String operateId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ScenarioMeta that = (ScenarioMeta) o;
        return Objects.equals(projectId, that.projectId) && Objects.equals(operateId, that.operateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, operateId);
    }
}
