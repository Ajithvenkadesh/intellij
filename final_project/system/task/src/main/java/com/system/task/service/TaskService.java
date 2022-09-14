package com.system.task.service;

import com.system.task.model.Task;
import java.util.ArrayList;

/**
 * Lists the services available for task.
 *
 * @author Ajith venkadesh
 * @version 1.0
 */
public interface TaskService {

    /**
     * Creates new task.
     *
     * @param task Model of task.
     * @return Success or failure.
     */
    int create(final Task task);

    /**
     * Displays all the tasks in the list.
     *
     * @return List of available tasks.
     */
    ArrayList<Task> display();

    /**
     * Updates task.
     *
     * @param task Model of task.
     */
    boolean update(final Task task);

    /**
     * Deletes a particular task by using id.
     *
     * @param task Model of the task.
     * @return Success or failure message.
     */
    boolean delete(final Task task);

    /**
     * Searches a particular task by using id.
     *
     * @param id Id of the task.
     * @return Model of task class.
     */
    Task get(final int id);

    /**
     * Searches task by status.
     *
     * @param taskStatus status of task.
     * @return List of tasks.
     */
    ArrayList<Task> searchTaskByStatus(final String taskStatus);
}
