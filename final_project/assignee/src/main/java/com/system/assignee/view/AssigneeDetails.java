package com.system.assignee.view;

import com.system.assignee.dao.AssigneeDao;
import com.system.assignee.model.Assignee;
import java.util.InputMismatchException;

/**
 * Gets details about the assignee like id of assignee,
 * name of assignee from user and also it generates the
 * assignee id automatically.
 *
 * @author Ajith venkadesh.
 * @version 1.0
 */
public class AssigneeDetails {

    /**
     * Gets the id of the assignee from the user for searching,
     * deleting, updating operations.
     *
     * @return id of the assignee.
     */
    public int getAssigneeId() {
        int assigneeId;

        try {
            AssigneeDao.LOGGER.info("Enter the id of the assignee");
            assigneeId = AssigneeMenu.INPUT.nextInt();
            AssigneeMenu.INPUT.nextLine();
        } catch (InputMismatchException exception) {
            AssigneeDao.LOGGER.warning("Only integer value is accepted enter ineger value");
            assigneeId = AssigneeMenu.INPUT.nextInt();
            AssigneeMenu.INPUT.nextLine();
        }
        return assigneeId;
    }

    /**
     * Gets the name of the assignee from the user for
     * inserting, updating operations.
     *
     * @return name of the assignee.
     */
    public String getAssigneeName() {
        AssigneeDao.LOGGER.info("Enter the name of the assignee");
        return AssigneeMenu.INPUT.nextLine();
    }

    /**
     * Prints the details about the assignee.
     *
     * @param assignee Model of assignee class.
     */
    void displayAssignee(final Assignee assignee) {
        if (assignee == null) {
            AssigneeDao.LOGGER.warning("No assignee record found");
        } else {
            AssigneeDao.LOGGER.info("Assignee details");
            AssigneeDao.LOGGER.info("Assignee id is : " + assignee.getAssigneeId());
            AssigneeDao.LOGGER.info("Assignee name is : " + assignee.getAssigneeName());
        }
    }

    /**
     * Gets the role of the assignee.
     *
     * @return Role of the assignee.
     */
    public String getRole() {
        AssigneeDao.LOGGER.info("Enter the role");
        return AssigneeMenu.INPUT.nextLine();
    }
}
