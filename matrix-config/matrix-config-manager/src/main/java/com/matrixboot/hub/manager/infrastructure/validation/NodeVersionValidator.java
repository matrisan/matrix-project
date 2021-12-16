package com.matrixboot.hub.manager.infrastructure.validation;

import com.matrixboot.hub.manager.infrastructure.version.BaseVersion;
import com.matrixboot.hub.manager.infrastructure.version.IRemoteVersion;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

/**
 * <p>
 * create in 2021/12/16 10:27 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
public class NodeVersionValidator implements ConstraintValidator<NodeVersion, String> {

    @Resource
    private Map<String, IRemoteVersion<? extends BaseVersion>> versionMap;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value) && versionMap.containsKey(value);
    }
}
