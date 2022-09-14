package com.taskManagementSystem.service.implementation;

import com.taskManagementSystem.dao.TaskDao;
import com.taskManagementSystem.model.Task;
import com.taskManagementSystem.service.TaskService;
import java.util.ArrayList;

/**
 * Provides implementation of task service.
 * 
 * @author Ajithvenkadesh
 * @version 1.0
 */
public class TaskImplementation implements TaskService {
    private static final TaskDao TASKDAO = new TaskDao();

    /**
     * Creates new task record.
     * 
     * @param task Model of task.
     * @return Success or failure message.
     */
	@Override
	public String create(final Task task) {
		return TASKDAO.create(task.getTaskName(), task.getTaskDescription(), task.getTaskStatus(),
					task.getTaskStartDate(), task.getTaskDueDate());
	}
	
	/**
	 * Displays all task.
	 * 
	 * @return List of tasks.
	 */
	@Override
	public ArrayList<Task> display() {
		return TASKDAO.display();
	}
	
	/**
	 * Deletes a particular task.
	 * 
	 * @param task Model of the task.
	 * @return Success or failure message.
	 */
	@Override
	public String delete(final Task task) 	{
		return (TASKDAO.delete(task.getTaskId())) ? "Assignee deleted successfully" : "Failed to delete";
	}
	
	/**
	 * Updates an existing task.
	 * 
	 * @param task Model of the task.
	 * @return Success or failure message.
	 */
	@Override
	public String update(final Task task) {
		return (TASKDAO.update(task.getTaskId(), task.getTaskName(), task.getTaskDescription(),
				task.getTaskStatus(), task.getTaskStartDate(), task.getTaskDueDate())) ?
				"Updated successfully" : "Failed to update";
	}
	
	/**
	 * Searches a particular task.
	 * 
	 * @param id Id of the task.
	 * @return Model of task.
	 */
	@Override
	public Task search(final int id) {
		return TASKDAO.search(id);
	}
	
	/**
	 * Searches task by status.
	 * 
	 * @param taskStatus Status of task.
	 * @return List of tasks.
	 */
	@Override
	public ArrayList<Task> searchTaskByStatus(final String taskStatus) {
		return TASKDAO.searchTaskByStatus(taskStatus);
	}
 }
