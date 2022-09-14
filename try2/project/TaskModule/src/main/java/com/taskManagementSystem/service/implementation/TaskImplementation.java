package com.taskManagementSystem.service.implementation;

import com.taskManagementSystem.dao.TaskDao;
import com.taskManagementSystem.model.Task;
import com.taskManagement.validation.Validator;
import com.taskManagementSystem.service.TaskService;
import java.util.ArrayList;

/**
 * Provides implementation of task service.
 * 
 * @author Ajithvenkadesh
 * @version 1.0
 */
public class TaskImplementation implements TaskService {
    private final TaskDao taskDao = new TaskDao();
	private final Validator validator = new Validator();

    /**
     * Creates new task record.
     * 
     * @param task Model of task.
     * @return Success or failure message.
     */
	@Override
	public String create(final Task task) {
		if (validator.validateName(task.getTaskName())) {
			return taskDao.create(task.getTaskName(), task.getTaskDescription(), task.getTaskStatus(),
					task.getTaskStartDate(), task.getTaskDueDate());
		} else {
			return "invalid details";
		}
	}
	
	/**
	 * Displays all task.
	 * 
	 * @return List of tasks.
	 */
	@Override
	public ArrayList<Task> display() {
		return taskDao.display();
	}
	
	/**
	 * Deletes a particular task.
	 * 
	 * @param task Model of the task.
	 * @return Success or failure message.
	 */
	@Override
	public String delete(final Task task) 	{
		if (validator.validateTaskId(task.getTaskId())) {
			return taskDao.delete(task.getTaskId());
		} else {
			return "Invalid Id";
		}
	}
	
	/**
	 * Updates an existing task.
	 * 
	 * @param task Model of the task.
	 * @return Success or failure message.
	 */
	@Override
	public String update(final Task task) {
		if (validator.validateTaskId(task.getTaskId())) {
			return taskDao.update(task.getTaskId(), task.getTaskName(), task.getTaskDescription(),
					task.getTaskStatus(), task.getTaskStartDate(), task.getTaskDueDate());
		} else {
			return "Invalid details";
		}
	}
	
	/**
	 * Searches a particular task.
	 * 
	 * @param id Id of the task.
	 * @return Model of task.
	 */
	@Override
	public Task search(final int id) {
		if (validator.validateTaskId(id)) {
			return taskDao.search(id);
		} else {
			return null;
		}
	}
	
	/**
	 * Searches task by status.
	 * 
	 * @param taskStatus Status of task.
	 * @return List of tasks.
	 */
	@Override
	public ArrayList<Task> searchTaskByStatus(final String taskStatus) {
		return taskDao.searchTaskByStatus(taskStatus);
	}
 }
