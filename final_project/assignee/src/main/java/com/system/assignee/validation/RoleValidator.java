package com.system.assignee.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = RoleValidatorImplementation.class)
@Retention(RUNTIME)
public @interface RoleValidator {

    String message() default "invalid role";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
