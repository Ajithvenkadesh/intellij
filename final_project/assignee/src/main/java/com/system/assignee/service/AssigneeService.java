package com.system.assignee.service;

import com.system.assignee.model.Assignee;
import java.util.ArrayList;

/**
 * Provides the services available for assignee.
 *
 * @author Ajith venkadesh
 * @version 1.0
 */
public interface AssigneeService {

    /**
     * Creates new assignee.
     *
     * @param assignee Model of assignee class.
     * @return Id of the newly created assignee.
     */
    int create(final Assignee assignee);

    /**
     * Deletes a particular assignee by using assignee id.
     *
     * @param assignee Model of the assignee.
     * @return Success or failure.
     */
    boolean delete(final Assignee assignee);

    /**
     * Updates a particular assignee by using assignee id.
     *
     * @param assignee Model of assignee.
     * @return Success or failure.
     */
    boolean update(final Assignee assignee);

    /**
     * Searches a particular assignee by using assignee id.
     *
     * @param id Id of the assignee.
     * @return Required assignee record.
     */
    Assignee get(final int id);

    /**
     * Lists all available assignees.
     *
     * @return List of all assignees.
     */
    ArrayList<Assignee> list();
}
