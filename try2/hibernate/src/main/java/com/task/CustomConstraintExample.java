package com.task;

import javax.validation.*;

public class CustomConstraintExample {
    private static final Validator validator;

    static {
        Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        validator = factory.getValidator();
        factory.close();
    }

    public static void start() {
        TestBean testBean = new TestBean();
        testBean.setLanguage("englis");
        validator.validate(testBean).stream().forEach(CustomConstraintExample::printError);
    }

    private static void printError(ConstraintViolation<TestBean> violation) {
        System.out.println(violation.getPropertyPath() + " " + violation.getMessage());
    }
}
