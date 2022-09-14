package com.system.assignee.view;

import com.system.assignee.controller.AssigneeController;
import com.system.assignee.model.Assignee;
import com.system.task.view.TaskMenu;

/**
 * List various operations available for
 * creating, reading, updating, deleting
 * assignee.
 *
 * @author Ajith venkadesh.
 * @version 1.0
 */
public class AssigneeMenu {

    /**
     * Display all options available for assignee.
     */
    public void dispalyOptions() {
        final AssigneeController controller = new AssigneeController();
        final TaskMenu menu = new TaskMenu();
        final AssigneeDetails details = new AssigneeDetails();

        MenuLauncher.LOGGER.info("\n Enter 1 for creating new assignee"
                + "\n Enter 2 for updating a partcular assignee"
                + "\n Enter 3 for deleting a particular assignee"
                + "\n Enter 4 searching a particular assignee"
                + "\n Enter * to move to task menu ");

        switch (MenuLauncher.INPUT.nextLine()) {
            case "1":
                final Assignee createAssignee = new Assignee(details.getAssigneeName(),
                        details.getRole());
                MenuLauncher.LOGGER.info(controller.createAssignee(createAssignee));
                break;
            case "2":
                final Assignee updateAssignee = new Assignee(details.getAssigneeId(),
                        details.getAssigneeName(), details.getRole());
                MenuLauncher.LOGGER.info(controller.updateAssignee(updateAssignee));
                break;
            case "3":
                final Assignee deleteAssignee = new Assignee(details.getAssigneeId());
                MenuLauncher.LOGGER.info(controller.deleteAssignee(deleteAssignee));
                break;
            case "4":
                details.print(controller.get(details.getAssigneeId()));
                break;
            case "*":
                menu.displayOptions();
                break;
            default:
                MenuLauncher.LOGGER.warning("You have enetered wrong choice");
                break;
        }
    }
}
