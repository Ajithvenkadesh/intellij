package com.system.assignee.service.impl;

import com.system.assignee.model.Assignee;
import com.system.assignee.service.AssigneeService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Provides implementation for Assignee service interface.
 *
 * @author Ajith venkadesh.
 * @version 1.0
 */
public class AssigneeImplementation implements AssigneeService {
    public static final ArrayList<Assignee> ASSIGNEELIST = new ArrayList<Assignee>();
    private static final AtomicInteger ASSIGNEEID = new AtomicInteger(0);

    /**
     * Generates assignee id automatically and in sequence.
     *
     * @return Id of the assignee.
     */
    public int generateAssigneeId() {
        return ASSIGNEEID.incrementAndGet();
    }

    /**
     * Creates new assignee by getting inputs from user.
     *
     * @param assignee Object of class assignee.
     * @return assignee id.
     */
    public int create(final Assignee assignee) {
        ASSIGNEELIST.add(assignee);
        return assignee.getAssigneeId();
    }

    /**
     * Deletes a particular assignee by using assignee id.
     *
     * @param deleteAssignee Model of the assignee.
     * @return success or failure.
     */
    public boolean delete(final Assignee deleteAssignee) {
        final Iterator<Assignee> currentAssignee = ASSIGNEELIST.iterator();

        while (currentAssignee.hasNext()) {
            final Assignee assigneeToBeDeleted = currentAssignee.next();

            if (assigneeToBeDeleted.getAssigneeId() == deleteAssignee.getAssigneeId()) {
                currentAssignee.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Updates a particular assignee by using assignee id.
     *
     * @param updateAssignee Model of the assignee.
     * @return success or failure.
     */
    public boolean update(final Assignee updateAssignee) {
        for (final Assignee assignee : ASSIGNEELIST) {
            if (assignee.getAssigneeId() == updateAssignee.getAssigneeId()) {
                if (!updateAssignee.getAssigneeName().equals("no")) {
                    assignee.setAssigneeName(updateAssignee.getAssigneeName());
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Searches a particular assignee by using assignee id.
     *
     * @param id Id of the assignee.
     * @return Required assignee record.
     */
    public Assignee get(final int id) {
        for (final Assignee assignee : ASSIGNEELIST) {
            if (assignee.getAssigneeId() == id) {
                return assignee;
            }
        }
        return null;
    }

    /**
     * Displays the list of assignee.
     *
     * @return List of assignees.
     */
    @Override
    public ArrayList<Assignee> list() {
        return ASSIGNEELIST;
    }
}
