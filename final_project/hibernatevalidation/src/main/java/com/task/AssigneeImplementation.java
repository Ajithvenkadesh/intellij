package com.task;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


public class AssigneeImplementation {
    public void initiate() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Assignee assignee = new Assignee(null, "Ajith", "Internddddddddddddddddddddddddd");
        Set<ConstraintViolation<Assignee>> constraintViolations = validator.validate(assignee);

        for(ConstraintViolation constraintViolation : constraintViolations) {
            String fieldName = constraintViolation.getPropertyPath().toString();
            System.out.println(fieldName + " " + constraintViolation.getMessage());
        }
    }
}