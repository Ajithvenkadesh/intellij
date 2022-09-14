package com.system.task.controller;

import com.system.task.model.Task;
import com.system.task.service.TaskService;
import com.system.task.service.implementation.TaskImplementation;
import com.validation.Validator;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import java.util.ArrayList;

/**
 * Provides implementation of Task service.
 *
 * @author Ajith venkadesh
 * @version 1.0
 */
public class TaskController {
    private static final TaskService TASKSERVICE = new TaskImplementation();
    private static final Validator VALIDATOR = new Validator();

    /**
     * Creates an new task record.
     *
     * @param task Model of task class.
     * @return Success or failure message.
     */
    @Path("/")
    @Consumes("application/json")
    @POST
    public String createTask(final Task task) {
        final int taskId;

        if (!VALIDATOR.validateName(task.getTaskName())) {
            return "invalid details";
        } else {
            taskId = TASKSERVICE.create(task);
            return (taskId > 0) ? "task created successfully" + taskId : "failed to create task";
        }
    }

    /**
     * Displays all the task.
     *
     * @return List of available tasks.
     */
    @Path("/list")
    @Produces("application/json")
    @GET
    public ArrayList<Task> listTask() {
        return TASKSERVICE.display();
    }

    /**
     * Deletes an existing task.
     *
     * @param task Model of the task.
     * @return Success or failure message.
     */
    @Path("/")
    @Consumes("application/json")
    @DELETE
    public String deleteTask(final Task task) {
        if (VALIDATOR.validateTaskId(task.getTaskId())) {
            return TASKSERVICE.delete(task) ? "Deleted successfully" : "Failed to delete";
        } else {
            return "invalid id";
        }
    }

    /**
     * Updates an existing task record.
     *
     * @param task Model of task.
     * @return Success or failure message.
     */
    @Path("/")
    @Consumes("application/json")
    @PUT
    public String updateTask(final Task task) {
        return TASKSERVICE.update(task) ? "Updated successfully" : " Failed to update";
    }

    /**
     * Searches a particular task.
     *
     * @param id Id of the task to be searched.
     * @return Required task.
     */
    @Path("/{id}")
    @Produces("application/json")
    @GET
    public Task searchParticularTask(@PathParam("id") final int id) {
        return (VALIDATOR.validateTaskId(id)) ? TASKSERVICE.get(id) : null;
    }

    /**
     * Searches task by status.
     *
     * @param taskStatus Status of the task.
     * @return list of tasks.
     */
    @Path("/status/{taskStatus}")
    @Produces("application/json")
    @GET
    public ArrayList<Task> searchTaskByStatus(@PathParam("taskStatus") final String taskStatus) {
        return TASKSERVICE.searchTaskByStatus(taskStatus);
    }
}
