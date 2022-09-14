package com.taskManagement.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.taskManagement.model.Assignee;
import com.taskManagement.model.Task;
import com.taskManagement.service.TaskAssignerService;

/**
 * Provides implementation for TaskAssigner service.
 *  
 * @author Ajith venkadesh
 * @version 1.0
 *
 */
public class TaskAssigner implements TaskAssignerService {
    final static HashMap<Assignee, ArrayList<Task>> TASKMAP = new HashMap<Assignee, ArrayList<Task>>();
	
	/**
	 * Creates a list of tasks for assigning to assignees.
	 * 
	 * @param taskIdList List of task id.
	 * @return list of required tasks.
	 */
	private ArrayList<Task> findTask(final int[] taskIdList){
		final ArrayList<Task> tasks = new ArrayList<Task>();
		final int totalNOfTask = taskIdList.length;
		
		for (int initialValue = 0; initialValue < totalNOfTask; initialValue++) {
            for (final Task currentTask : TaskImplementation.TASKCOLLECTION) {
		    	if (currentTask.getTaskId() == taskIdList[initialValue]) {
		    		Task requiredTask = currentTask;
		    		tasks.add(requiredTask);
		    	}
            }
		}
		return tasks;
	}
	
	/**
	 * Assigns task to assignee using assignee id 
	 * and task id.
	 * 
	 * @param assigneeId Id of the assignee.
	 * @param taskIdList list of task id.
	 */
	public void assignTask(final int assigneeId, final int[] taskIdList) {
		Assignee assignee = null;
		final Iterator<Assignee> currentAssignee = AssigneeImplementation.ASSIGNEELIST.iterator();
						
		while (currentAssignee.hasNext()) {
			final Assignee nextAssignee = currentAssignee.next();
			
			if (nextAssignee.getAssigneeId() == assigneeId) {
				assignee = nextAssignee;
			}
		}
		TASKMAP.put(assignee, findTask(taskIdList));
	}
	
	/**
	 * Searches task assigned to assignee using
	 * assignee id. 
	 * 
	 * @param assigneeId Id of the assignee.
	 * @return List of required task
	 */
	public ArrayList<Task> searchTaskByAssigneeId(final int assigneeId) {
        ArrayList<Task> task = null;
        
        for (final Map.Entry<Assignee, ArrayList<Task>> entry : TASKMAP.entrySet()) {
			if (entry.getKey().getAssigneeId() == assigneeId) {
				task = entry.getValue();
			}
		}
		return task;
	}
}
