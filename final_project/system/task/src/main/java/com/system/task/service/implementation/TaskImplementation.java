package com.system.task.service.implementation;

import com.system.task.dao.TaskDao;
import com.system.task.model.Task;
import com.system.task.service.TaskService;
import java.util.ArrayList;

/**
 * Provides implementation of task service.
 *
 * @author Ajithvenkadesh
 * @version 1.0
 */
public class TaskImplementation implements TaskService {
    private static final TaskDao TASKDAO = new TaskDao();

    /**
     * Creates new task record.
     *
     * @param task Model of task.
     * @return Success or failure message.
     */
    @Override
    public int create(final Task task) {
        return TASKDAO.create(task.getTaskName(), task.getTaskDescription(), task.getTaskStatus(),
                task.getTaskStartDate(), task.getTaskDueDate());
    }

    /**
     * Displays all task.
     *
     * @return List of tasks.
     */
    @Override
    public ArrayList<Task> display() {
        return TASKDAO.display();
    }

    /**
     * Deletes a particular task.
     *
     * @param task Model of the task.
     * @return Success or failure message.
     */
    @Override
    public boolean delete(final Task task) {
        return TASKDAO.delete(task.getTaskId());
    }

    /**
     * Updates an existing task.
     *
     * @param task Model of the task.
     * @return Success or failure message.
     */
    @Override
    public boolean update(final Task task) {
        return (TASKDAO.update(task.getTaskId(), task.getTaskName(), task.getTaskDescription(),
                task.getTaskStatus(), task.getTaskStartDate(), task.getTaskDueDate()));
    }

    /**
     * Searches a particular task.
     *
     * @param id Id of the task.
     * @return Model of task.
     */
    @Override
    public Task get(final int id) {
        return TASKDAO.get(id);
    }

    /**
     * Searches task by status.
     *
     * @param taskStatus Status of task.
     * @return List of tasks.
     */
    @Override
    public ArrayList<Task> searchTaskByStatus(final String taskStatus) {
        return TASKDAO.searchTaskByStatus(taskStatus);
    }
}
