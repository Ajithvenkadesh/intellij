package com.system.assignee.service.implementation;

import com.system.assignee.dao.AssigneeDao;
import com.system.assignee.model.Assignee;
import com.system.assignee.service.AssigneeService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import java.util.ArrayList;
import java.util.Set;

/**
 * Provides implementation for assignee.
 *
 * @author Ajithvenkadesh
 * @version 1.0
 */
public class AssigneeImplementation implements AssigneeService {
    private static final AssigneeDao ASSIGNEEDAO = new AssigneeDao();

    /**
     * Creates a new assignee record.
     *
     * @param assignee Model of assignee.
     * @return Success or failure message
     */
    public int create(final Assignee assignee) {
        try (ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure().buildValidatorFactory()) {
            final Validator validator = validatorFactory.getValidator();
            final Set<ConstraintViolation<Assignee>> constraintViolations = validator.validate(assignee);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Assignee> violation : constraintViolations) {
                    AssigneeDao.LOGGER.warning(violation.getMessage());
                }
                return 0;
            } else {
                return ASSIGNEEDAO.create(assignee);
            }
        }
    }

    /**
     * Deletes an existing assignee record.
     *
     * @param assignee Model of the assignee.
     * @return Success or failure message.
     */
    public boolean delete(final Assignee assignee) {
        return ASSIGNEEDAO.delete(assignee);
    }

    /**
     * Updates an existing assignee record.
     *
     * @param assignee Model of assignee.
     * @return Success or failure message.
     */
    public boolean update(Assignee assignee) {
        try (ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure().buildValidatorFactory()) {
            final Validator validator = validatorFactory.getValidator();
            final Set<ConstraintViolation<Assignee>> constraintViolations = validator.validate(assignee);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Assignee> violation : constraintViolations) {
                    AssigneeDao.LOGGER.warning(violation.getMessage());
                }
                return false;
            } else {
                return ASSIGNEEDAO.update(assignee);
            }
        }
    }

    /**
     * Finds a particular assignee record.
     *
     * @param id Id of the assignee.
     * @return Model of assignee.
     */
    public Assignee get(int id) {
        return ASSIGNEEDAO.get(id);
    }

    /**
     * Displays all the assignee record.
     *
     * @return list of assignees.
     */
    public ArrayList<Assignee> list() {
        return ASSIGNEEDAO.list();
    }
}
