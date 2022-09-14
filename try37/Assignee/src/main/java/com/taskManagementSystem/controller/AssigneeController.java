package com.taskManagementSystem.controller;

import com.taskManagementSystem.model.Assignee;
import com.taskManagementSystem.service.AssigneeService;
import com.taskManagementSystem.service.implementation.AssigneeImplementation;

import javax.ws.rs.*;
import java.util.ArrayList;

/**
 * Invokes the Services available for Assignee.
 * 
 * @author Ajith venkadesh
 * @version 1.0
 */
public class AssigneeController {
	private final AssigneeService assigneeService = new AssigneeImplementation();
		
	/**
	 * Creates new assignee record.
	 * 
	 * @param assignee Model of assignee.
	 * @return Success or failure message.
	 */
	@Path("/")
	@Consumes("application/json")
	@POST
	public String createAssignee(final Assignee assignee) {
		return assigneeService.create(assignee);
	}
	
	/**
	 *Deletes an existing assignee record.
	 * 
	 * @param assignee Model of the assignee.
	 * @return Success or failure message.
	 */
	@Path("/")
	@Consumes("application/json")
	@DELETE
	public String deleteAssignee(final Assignee assignee) {
		return assigneeService.delete(assignee);
	}
	
	/**
	 * Updates an existing assignee record.
	 * 
	 * @param assignee Model of the assignee.
	 * @return Success or failure message.
	 */
	@Path("/")
	@Consumes("application/json")
	@PUT
	public String updateAssignee(final Assignee assignee) {
		return assigneeService.update(assignee);
	}
	
	/**
	 * Search a particular assignee record.
	 * 
	 * @param id Id of the assignee.
	 * @return Required assignee record.
	 */
	@Path("/{id}")
	@Produces("application/json")
	@GET
	public Assignee searchParticularAssignee(@PathParam("id") final int id) {
		return assigneeService.search(id);
	}

	/**
	 * Displays all the assignees.
	 *
	 * @return List of assignees.
	 */
	@Path("/list")
	@Produces("application/json")
	@GET
	public ArrayList<Assignee> list() {
		return assigneeService.list();
	}
}
