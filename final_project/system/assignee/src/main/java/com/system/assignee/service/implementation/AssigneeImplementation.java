package com.system.assignee.service.implementation;

import com.system.assignee.dao.AssigneeDao;
import com.system.assignee.model.Assignee;
import com.system.assignee.service.AssigneeService;
import java.util.ArrayList;

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
        return ASSIGNEEDAO.create(assignee);
    }

    /**
     * Deletes an existing assignee record.
     *
     * @param assignee Model of the assignee.
     * @return Success or failure message.
     */
    public boolean delete(final Assignee assignee) {
        return ASSIGNEEDAO.delete(assignee.getAssigneeId());
    }

    /**
     * Updates an existing assignee record.
     *
     * @param assignee Model of assignee.
     * @return Success or failure message.
     */
    public boolean update(Assignee assignee) {
        return ASSIGNEEDAO.update(assignee.getAssigneeId(), assignee.getAssigneeName(),
                assignee.getRole());
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
