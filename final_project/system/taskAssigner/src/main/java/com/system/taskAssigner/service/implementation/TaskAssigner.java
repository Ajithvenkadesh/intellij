package com.system.taskAssigner.service.implementation;

import com.system.taskAssigner.dao.TaskAssignerDao;
import com.system.taskAssigner.service.TaskAssignerService;
import com.system.task.model.Task;
import com.system.taskAssigner.model.TaskAssignerModel;
import java.util.ArrayList;

/**
 * Assigns task to assignee and searches task
 * assigned to assignee using assignee id.
 *
 * @author Ajithvenkadesh
 * @version 1.0
 */
public class TaskAssigner implements TaskAssignerService {
    final TaskAssignerDao ASSIGNERDAO = new TaskAssignerDao();

    /**
     * Assigns task to assignee.
     *
     * @param taskAssignerModel Id of the assignee.
     */
    public void assignTask(final TaskAssignerModel taskAssignerModel) {
        ASSIGNERDAO.assignTask(taskAssignerModel.getAssigneeId(),
                taskAssignerModel.getTaskIdList());
    }

    /**
     * Searches task by assignee id.
     *
     * @param id Id of the assignee.
     * @return List of task.
     */
    public ArrayList<Task> searchTaskByAssigneeId(final int id) {
        return ASSIGNERDAO.searchTaskByAssigneeId(id);
    }
}
