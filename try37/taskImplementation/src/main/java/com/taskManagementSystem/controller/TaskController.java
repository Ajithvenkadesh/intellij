package com.taskManagementSystem.controller;

import java.util.ArrayList;
import com.taskManagementSystem.model.Task;
import com.taskManagementSystem.service.TaskService;
import com.taskManagementSystem.service.implementation.TaskImplementation;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

/**
 * Provides implementation of Task service.
 * 
 * @author Ajith venkadesh
 * @version 1.0
 */
public class TaskController {
	private static final TaskService TASKSERVICE = new TaskImplementation();
		
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
		return TASKSERVICE.create(task);
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
		return TASKSERVICE.delete(task);
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
		return TASKSERVICE.update(task);
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
		return TASKSERVICE.search(id);
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
