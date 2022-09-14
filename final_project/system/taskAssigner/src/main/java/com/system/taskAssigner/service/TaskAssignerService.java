package com.system.taskAssigner.service;

import com.system.task.model.Task;
import com.system.taskAssigner.model.TaskAssignerModel;
import java.util.ArrayList;

/**
 * Lists the services available for mapping a task.
 *
 * @author Ajith venkadesh.
 * @version 1.0
 */
public interface TaskAssignerService {

    /**
     * Assigns task to assignees.
     *
     * @param taskAssignerModel Model of task assigner.
     */
    void assignTask(final TaskAssignerModel taskAssignerModel);

    /**
     * Searches task assigned to assignee by using
     * assignee id.
     *
     * @param assigneeId Id of the assignee.
     * @return list of tasks.
     */
    ArrayList<Task> searchTaskByAssigneeId(final int assigneeId);
}
