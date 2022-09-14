package com.system.assignee.view;

import com.system.assignee.controller.AssigneeController;
import com.system.assignee.dao.AssigneeDao;
import com.system.assignee.model.Assignee;
import java.util.Scanner;

/**
 * List various operations available for
 * creating, reading, updating, deleting
 * assignee.
 *
 * @author Ajith venkadesh.
 * @version 1.0
 */
public class AssigneeMenu {
    public static final Scanner INPUT = new Scanner(System.in);

    /**
     * Display all options available for assignee.
     */
    public void dispalyOptions() {
        final AssigneeController controller = new AssigneeController();
        final AssigneeDetails details = new AssigneeDetails();

        AssigneeDao.LOGGER.info("\n Enter 1 for creating new assignee"
                + "\n Enter 2 for updating a partcular assignee"
                + "\n Enter 3 for deleting a particular assignee"
                + "\n Enter 4 searching a particular assignee");

        switch (INPUT.nextLine()) {
            case "1":
                final Assignee createAssignee = new Assignee(details.getAssigneeName(),
                        details.getRole());
                AssigneeDao.LOGGER.info(controller.createAssignee(createAssignee).toJSONString());
                break;
            case "2":
                final Assignee updateAssignee = new Assignee(details.getAssigneeId(),
                        details.getAssigneeName(), details.getRole());
                AssigneeDao.LOGGER.info(controller.updateAssignee(updateAssignee).toJSONString());
                break;
            case "3":
                final Assignee deleteAssignee = new Assignee(details.getAssigneeId());
                AssigneeDao.LOGGER.info(controller.deleteAssignee(deleteAssignee).toJSONString());
                break;
            case "4":
                details.displayAssignee(controller.get(details.getAssigneeId()));
                break;
            default:
                AssigneeDao.LOGGER.warning("You have enetered wrong choice");
                break;
        }
    }
}
