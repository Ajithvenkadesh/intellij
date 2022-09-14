package com.system.assignee.controller;

import com.system.assignee.model.Assignee;
import com.system.assignee.service.AssigneeService;
import com.system.assignee.service.implementation.AssigneeImplementation;
import com.validation.Validator;
import javax.ws.rs.Produces;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import java.util.ArrayList;

/**
 * Invokes the Services available for Assignee.
 *
 * @author Ajith venkadesh
 * @version 1.0
 */
public class AssigneeController {
    private final static AssigneeService ASSIGNEESERVICE = new AssigneeImplementation();
    private final static Validator VALIDATOR = new Validator();

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
        final int assigneeId;

        if (!VALIDATOR.validateName(assignee.getAssigneeName())) {
            return "invalid details";
        } else {
            assigneeId = ASSIGNEESERVICE.create(assignee);
            return (assigneeId > 0) ? "assignee created successfully" + assigneeId :
                    "failed to create assignee";
        }
    }

    /**
     * Deletes an existing assignee record.
     *
     * @param assignee Model of the assignee.
     * @return Success or failure message.
     */
    @Path("/")
    @Consumes("application/json")
    @DELETE
    public String deleteAssignee(final Assignee assignee) {
        if (VALIDATOR.validateAssigneeId(assignee.getAssigneeId())) {
            return ASSIGNEESERVICE.delete(assignee) ? "Deleted successfully" : "Invalid id";
        } else {
            return "invalid details";
        }
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
        if (VALIDATOR.validateAssigneeId(assignee.getAssigneeId())) {
            return ASSIGNEESERVICE.update(assignee) ? "Updated successfully" : "Failed to update";
        } else {
            return "invalid details";
        }
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
    public Assignee get(@PathParam("id") final int id) {
        return ASSIGNEESERVICE.get(id);
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
        return ASSIGNEESERVICE.list();
    }
}
