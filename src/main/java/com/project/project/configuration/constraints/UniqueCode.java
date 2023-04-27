package com.project.project.configuration.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueCodeValidator.class)
@Documented
public @interface UniqueCode {
    String message() default "The value is not unique";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
