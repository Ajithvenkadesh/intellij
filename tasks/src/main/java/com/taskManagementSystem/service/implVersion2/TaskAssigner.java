package com.taskManagement.service.implVersion2;

import java.util.ArrayList;
import com.taskManagement.dao.TaskAssignerDao;
import com.taskManagement.model.Task;
import com.taskManagement.service.TaskAssignerService;
import com.taskManagement.service.implVersion2.TaskAssigner;

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
	 * @param assigneeId Id of the assignee.
	 * @param taskIdList List of task id.
	 */
	public void assignTask(final int assigneeId, final int[] taskIdList) {
		ASSIGNERDAO.assignTask(assigneeId, taskIdList);
	}
	
	/**
	 * Searches task by assignee id.
	 * 
	 * @param assigneeId Id of the assignee.
	 * @return List of task.
	 */
	public ArrayList<Task> searchTaskByAssigneeId(final int assigneeId) {
		return ASSIGNERDAO.searchTaskByAssigneeId(assigneeId);
	}
}
