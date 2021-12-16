package com.matrixboot.hub.manager.infrastructure.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * create in 2021/12/16 10:27 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NodeVersionValidator.class)
public @interface NodeVersion {

    String message() default "版本号不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
