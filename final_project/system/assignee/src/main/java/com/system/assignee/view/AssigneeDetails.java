package com.system.assignee.view;

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
        int assigneeId = 0;

        try {
            MenuLauncher.LOGGER.info("Enter the id of the assignee");
            assigneeId = MenuLauncher.INPUT.nextInt();
            MenuLauncher.INPUT.nextLine();
        } catch (InputMismatchException exception) {
            MenuLauncher.LOGGER.warning("Only integer value is accepted enter ineger value");
            assigneeId = MenuLauncher.INPUT.nextInt();
            MenuLauncher.INPUT.nextLine();
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
        MenuLauncher.LOGGER.info("Enter the name of the assignee");
        return MenuLauncher.INPUT.nextLine();
    }

    /**
     * Prints the message.
     *
     * @param message Message to be printed.
     */
    public void printMessage(final String message) {
        MenuLauncher.LOGGER.info(message);
    }

    /**
     * Prints the details about the assignee.
     *
     * @param assignee Model of assignee class.
     */
    void print(final Assignee assignee) {
        if (assignee == null) {
            MenuLauncher.LOGGER.warning("No assignee record found");
        } else {
            MenuLauncher.LOGGER.info("Assignee details");
            MenuLauncher.LOGGER.info("Assignee id is : " + assignee.getAssigneeId());
            MenuLauncher.LOGGER.info("Assignee name is : " + assignee.getAssigneeName());
        }
    }

    /**
     * Gets the role of the assignee.
     *
     * @return Role of the assignee.
     */
    public String getRole() {
        MenuLauncher.LOGGER.info("Enter the role");
        return MenuLauncher.INPUT.nextLine();
    }
}
