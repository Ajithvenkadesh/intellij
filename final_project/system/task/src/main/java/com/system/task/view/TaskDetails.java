package com.system.task.view;

import com.system.exception.InvalidDescriptionException;
import com.system.task.dao.TaskDao;
import com.system.task.model.Task;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Get details about the task from the user like
 * task id, task name,task description,task status,
 * task start date, task due date.
 *
 * @author Ajith venkadesh.
 * @version 1.0
 */
public class TaskDetails {
    public static final Scanner INPUT = new Scanner(System.in);

	private enum TaskStatus {
		Under_progress, Under_review, Completed;
	}

    /**
     * Gets the task id from the user for delete,
     * update, search operations.
     *
     * @return id of the task.
     * @throws InputMismatchException when invalid input is entered.
     */
    public int getTaskId() {
        int taskId = 0;

        try {
            TaskDao.LOGGER.info("Enter task id : ");
            taskId = INPUT.nextInt();
            INPUT.nextLine();
        } catch (InputMismatchException exception) {
            TaskDao.LOGGER.warning("Only integer value is accepted");
            TaskDao.LOGGER.info("Enter task id : ");
            taskId = INPUT.nextInt();
            INPUT.nextLine();
        }
        return taskId;
    }

    /**
     * Gets the task name from the user.
     *
     * @return Name of the task.
     */
    public String getTaskName() {
        TaskDao.LOGGER.info("Enter task Name : ");
        return INPUT.nextLine();
    }

    /**
     * Gets the task description from the user.
     *
     * @return Description about the task.
     */
    String getTaskDescription() {
        TaskDao.LOGGER.info("Enter the task description :");
        String taskDescription = INPUT.nextLine();

        try {
            if (taskDescription.length() < 10) {
                throw new InvalidDescriptionException("Too short description enter valid description");
            }
        } catch (InvalidDescriptionException exception) {
            TaskDao.LOGGER.warning("Enter valid description");
            taskDescription = INPUT.nextLine();
        }
        return taskDescription;
    }

    /**
     * Gets the status of the task from the user.
     *
     * @return status of the task.
     * @throws InputMismatchException         when invalid input is entered.
     * @throws ArrayIndexOutOfBoundsException when wrong index is entered.
     */
    String getTaskStatus() {
        int taskIndex = 0;

        TaskDao.LOGGER.info("The list of status avaialable are : ");

        for (TaskStatus status : TaskStatus.values()) {
            TaskDao.LOGGER.info("ENTER  " + status.ordinal() + "  for   " + status);
        }

        try {
            TaskDao.LOGGER.info("Enter the value ");
            taskIndex = INPUT.nextInt();
            INPUT.nextLine();
        } catch (InputMismatchException exception) {
            TaskDao.LOGGER.warning("Enter only integer value");
            taskIndex = INPUT.nextInt();
            INPUT.nextLine();
        }

        try {
            return TaskStatus.values()[taskIndex].toString();
        } catch (ArrayIndexOutOfBoundsException exception) {
            TaskDao.LOGGER.warning("Enter only listed value");
            taskIndex = INPUT.nextInt();
            INPUT.nextLine();
            return TaskStatus.values()[taskIndex].toString();
        }
    }

    /**
     * Gets the due date from the user in string format.
     *
     * @return Date in string format.
     */
    public Date getDueDate() {
        TaskDao.LOGGER.info(" Enter Task Due Date : ");

        try {
            final SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            return formatDate.parse(INPUT.nextLine());
        } catch (ParseException e) {
            TaskDao.LOGGER.warning("You have entered wrong date format enter date in " +
                    "yyyy-MM-dd format");
            getDueDate();
        }
        return null;
    }

    /**
     * Gets the start date of the task from the user in string format.
     *
     * @return Start date of the task.
     */
    public Date getStartDate() {
        TaskDao.LOGGER.info(" Enter task start Date : ");

        try {
            final SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            return formatDate.parse(INPUT.nextLine());
        } catch (ParseException e) {
            TaskDao.LOGGER.warning("You have entered wrong date format enter date in " +
                    "yyyy-MM-dd format");
            getStartDate();
        }
        return null;
    }

    /**
     * Prints the message.
     *
     * @param message Message to be printed.
     */
    public void printMessage(final String message) {
        TaskDao.LOGGER.info(message);
    }

    /**
     * Prints the details of task.
     *
     * @param task Object of task class.
     */
    void printDetails(final Task task) {
        if (task == null) {
            TaskDao.LOGGER.warning("No task found");
        } else {
            TaskDao.LOGGER.info("Task details");
            TaskDao.LOGGER.info("Task id : " + task.getTaskId());
            TaskDao.LOGGER.info("Task name : " + task.getTaskName());
            TaskDao.LOGGER.info("Task description : " + task.getTaskDescription());
            TaskDao.LOGGER.info("Task start date : " + task.getTaskStartDate());
            TaskDao.LOGGER.info("Task due date : " + task.getTaskDueDate());
            TaskDao.LOGGER.info("Task status : " + task.getTaskStatus());
        }
    }

    /**
     * Prints all the available task.
     *
     * @param taskCollection List of all available tasks.
     */
    void printAllTask(final ArrayList<Task> taskCollection) {
        if (taskCollection.isEmpty()) {
            TaskDao.LOGGER.warning("No task found");
        } else {
            for (final Task task : taskCollection) {
                TaskDao.LOGGER.info("Task details");
                TaskDao.LOGGER.info("task id : " + task.getTaskId());
                TaskDao.LOGGER.info("Task name is : " + task.getTaskName());
                TaskDao.LOGGER.info("Task Description is : " + task.getTaskDescription());
                TaskDao.LOGGER.info("Task start date is : " + task.getTaskStartDate());
                TaskDao.LOGGER.info("Task due date is : " + task.getTaskDueDate());
                TaskDao.LOGGER.info("Task status is : " + task.getTaskStatus());
            }
        }
    }
}
