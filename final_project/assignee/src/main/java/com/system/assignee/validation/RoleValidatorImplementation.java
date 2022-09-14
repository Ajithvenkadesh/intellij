package com.system.assignee.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Provides logic to validate the assignee role.
 */
public class RoleValidatorImplementation implements ConstraintValidator<RoleValidator, String> {

    /**
     * Validates the role of the assignee.
     *
     * @param role    object to validate
     * @param context context in which the constraint is evaluated
     * @return wheteher the role is valid or not.
     */
    @Override
    public boolean isValid(String role, ConstraintValidatorContext context) {
        return "intern".equalsIgnoreCase(role) ||
                "junior developer".equalsIgnoreCase(role);
    }
}