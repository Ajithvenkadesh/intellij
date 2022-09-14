package com.system.assignee.controller;

import com.system.assignee.model.Assignee;
import com.system.assignee.service.AssigneeService;
import com.system.assignee.service.implementation.AssigneeImplementation;
import org.json.simple.JSONObject;
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

    /**
     * Creates new assignee record.
     *
     * @param assignee Model of assignee.
     * @return Success or failure message.
     */
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    @POST
    public JSONObject createAssignee(final Assignee assignee) {
        final int assigneeId = ASSIGNEESERVICE.create(assignee);
        final JSONObject result = new JSONObject();

        if (assigneeId > 0) {
            result.put("Success", "true");
            result.put("Data", "created assignee successfully assignee id is : " + assigneeId);
        } else {
            result.put("Success", "false");
            result.put("Data", "failed to create assignee");
        }
        return result;
    }

    /**
     * Deletes an existing assignee record.
     *
     * @param assignee Model of the assignee.
     * @return Success or failure message.
     */
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    @DELETE
    public JSONObject deleteAssignee(final Assignee assignee) {
        final JSONObject result = new JSONObject();

        if (ASSIGNEESERVICE.delete(assignee)) {
            result.put("Success", "true");
            result.put("Data", "successfully deleted assignee");
        } else {
            result.put("Success", "false");
            result.put("Data", "failed to delete assignee");
        }
        return result;
    }

    /**
     * Updates an existing assignee record.
     *
     * @param assignee Model of the assignee.
     * @return Success or failure message.
     */
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    @PUT
    public JSONObject updateAssignee(final Assignee assignee) {
        final JSONObject result = new JSONObject();

        if (ASSIGNEESERVICE.update(assignee)) {
            result.put("Success", "true");
            result.put("Data", "successfully updated assignee");
        } else {
            result.put("Success", "false");
            result.put("Data", "failed to update assignee");
        }
        return result;
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
