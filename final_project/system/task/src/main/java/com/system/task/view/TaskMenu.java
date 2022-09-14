package com.system.task.view;

import com.system.assignee.view.AssigneeMenu;
import com.system.task.controller.TaskController;
import com.system.task.dao.TaskDao;
import com.system.task.model.Task;

/**
 * List various operations available for
 * creating, reading, updating, deleting
 * task.
 *
 * @author Ajith venkadesh.
 * @version 1.0
 */
public class TaskMenu {

    /**
     * Displays all the options available for editing task,
     */
    public void displayOptions() {
        final TaskController controller = new TaskController();
        final AssigneeMenu menu = new AssigneeMenu();
        final TaskDetails details = new TaskDetails();

        TaskDao.LOGGER.info("\n Enter 1 for creating new task"
                + "\n Enter 2 for displaying all task"
                + "\n Enter 3 for updating a particular task"
                + "\n Enter 4 for deleting a particular task"
                + "\n Enter 5 searching a particular task"
                + "\n Enter 6 to search task by status"
                + "\n Enter 7 to list the task assigned to assignee"
                + "\n Enter * to move to assignee menu ");

        switch (TaskDetails.INPUT.nextLine()) {
            case "1":
                final Task task = new Task(details.getTaskName(), details.getTaskDescription(),
                        details.getStartDate(), details.getDueDate(), details.getTaskStatus());
                TaskDao.LOGGER.info(controller.createTask(task));
                break;
            case "2":
                details.printAllTask(controller.listTask());
                break;
            case "3":
                Task updateTask = new Task(details.getTaskId(), details.getTaskName(),
                        details.getTaskDescription(), details.getStartDate(), details.getDueDate(),
                        details.getTaskStatus());
                controller.updateTask(updateTask);
                break;
            case "4":
                Task deleteTask = new Task(details.getTaskId());
                TaskDao.LOGGER.info(controller.deleteTask(deleteTask));
                break;
            case "5":
                details.printDetails(controller.searchParticularTask(details.getTaskId()));
                break;
            case "6":
                details.printAllTask(controller.searchTaskByStatus(details.getTaskStatus()));
                break;
            case "7":
                menu.dispalyOptions();
                break;
            default:
                TaskDao.LOGGER.warning("You have entered wrong choice");
                break;
        }
    }
}
