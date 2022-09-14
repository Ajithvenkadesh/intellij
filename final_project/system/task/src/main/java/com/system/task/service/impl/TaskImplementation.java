package com.system.task.service.impl;

import com.system.task.model.Task;
import com.system.task.service.TaskService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Performs create, read, update, delete operations
 * in the task class.
 *
 * @author Ajith venkadesh.
 * @version 1.0
 */
public class TaskImplementation implements TaskService {
    public static final ArrayList<Task> TASKCOLLECTION = new ArrayList<Task>();
    static final AtomicInteger TASKID = new AtomicInteger(0);

    /**
     * Generates task id automatically and in sequence.
     *
     * @return Id of the task.
     */
    public int generateTaskId() {
        return TASKID.incrementAndGet();
    }

    /**
     * Creates new task by getting inputs from user.
     *
     * @param task Model of task class.
     * @return Success or failure message.
     */
    public int create(Task task) {
        TASKCOLLECTION.add(task);
        return task.getTaskId();
    }

    /**
     * Displays all the tasks in the list.
     */
    public ArrayList<Task> display() {
        return TASKCOLLECTION;
    }

    /**
     * Updates a particular task by using task id.
     *
     * @param updateTask Task to be updated.
     * @return Succes or failure.
     */
    public boolean update(final Task updateTask) {
        for (final Task task : TASKCOLLECTION) {
            if (task.getTaskId() == updateTask.getTaskId()) {
                if (!updateTask.getTaskName().equals("no")) {
                    task.setTaskName(updateTask.getTaskName());
                }

                if (!updateTask.getTaskDescription().equals("no")) {
                    task.setTaskDescription(updateTask.getTaskDescription());
                }

                if (!updateTask.getTaskStatus().equals("")) {
                    task.setTaskStatus(updateTask.getTaskStatus());
                }

                if (!updateTask.getTaskStartDate().equals("no")) {
                    task.setTaskStartDate(updateTask.getTaskStartDate());
                }

                if (!updateTask.getTaskDueDate().equals("no")) {
                    task.setTaskDueDate(updateTask.getTaskDueDate());
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Deletes a particular task by using id.
     *
     * @param deleteTask Model of task.
     * @return Success or failure.
     */
    public boolean delete(final Task deleteTask) {
        final Iterator<Task> currentTask = TASKCOLLECTION.iterator();

        while (currentTask.hasNext()) {
            final Task taskToBeDeleted = currentTask.next();

            if (taskToBeDeleted.getTaskId() == deleteTask.getTaskId()) {
                currentTask.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Searches a particular task by using id.
     *
     * @param id Id of the assignee
     * @return Details about particular task.
     */
    public Task get(final int id) {
        for (final Task task : TASKCOLLECTION) {
            if (task.getTaskId() == id) {
                return task;
            }
        }
        return null;
    }

    /**
     * Searches task by status.
     *
     * @param status status of the task.
     */
    public ArrayList<Task> searchTaskByStatus(final String status) {
        final ArrayList<Task> taskList = new ArrayList<Task>();

        for (final Task task : TASKCOLLECTION) {
            if (status.equals(task.getTaskStatus())) {
                taskList.add(task);
            }
        }
        return taskList;
    }
}
