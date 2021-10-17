package com.matrixboot.strategy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * <p>
 * create in 2021/10/16 11:32 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScenarioMetaEntity {

    private String projectId;

    private String operateId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ScenarioMetaEntity that = (ScenarioMetaEntity) o;
        return Objects.equals(projectId, that.projectId) && Objects.equals(operateId, that.operateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, operateId);
    }
}
