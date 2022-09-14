package com.taskManagement.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import com.taskManagement.exception.descriptionException.InvalidDescriptionException;
import com.taskManagement.model.Task;

/**
 * Gets details about the task from the user like
 * task id, task name,task description,task status,
 * task start date, task due date and also it generates
 * date from from string format .
 *  
 * @author Ajith venkadesh.
 * @version 1.0
 */
public class TaskDetails {
	private enum TaskStatus {
		Under_progress, Under_review, Completed;
	}
		
	/**
	 * Gets the task id from the user for delete,
	 * update, search operations.
	 * 
	 * @return id of the task.
	 * @throws InputMismatchException when invalid input is entered.
	 */
	public int getTaskId() {
		int taskId = 0 ;
		
		try {
			System.out.println ("Enter task id : ");
		    taskId = MenuLauncher.INPUT.nextInt();
		    MenuLauncher.INPUT.nextLine();
		} catch (InputMismatchException exception) {
			System.out.println ("Only integer value is accepted");
			taskId = MenuLauncher.INPUT.nextInt();
		    MenuLauncher.INPUT.nextLine();
		}
		return taskId;
	}
	
	/**
	 * Gets the task name from the user.
	 * 
	 * @return Name of the task.
	 */
	public String getTaskName() {
		System.out.println ("Enter task Name : ");
		return MenuLauncher.INPUT.nextLine();
	}
	
	/**
	 * Gets the task description from the user.
	 * 
	 * @return Description about the task.
	 */
	String getTaskDescription() {
		System.out.println ("Enter the task description :");
		final String taskDescription = MenuLauncher.INPUT.nextLine();
		
		try {
		    if (taskDescription.length() < 10) {
			throw new InvalidDescriptionException("Too short description enter valid description");
		    }
		} catch (InvalidDescriptionException exception) {
			getTaskDescription();
		}
		return taskDescription;
	}
	
	/**
	 * Gets the status of the task from the user.
	 * 
	 * @return status of the task.
	 * @throws InputMismatchException when invalid input is entered.
	 * @throws ArrayIndexOutOfBoundsException when wrong index is entered.
	 */
	String getTaskStatus() {
		int index = 0;
		System.out.println ("The list of status avaialable are : ");
		
		for (TaskStatus status : TaskStatus.values()) {
			System.out.println ("ENTER  " + status.ordinal() + "  for   " + status);
		}
		
		try {
			System.out.println ("Enter the value ");
		    index = MenuLauncher.INPUT.nextInt();
		    MenuLauncher.INPUT.nextLine();
		} catch (InputMismatchException exception) {
			System.out.println ("Enter only integer value");
			index = MenuLauncher.INPUT.nextInt();
		    MenuLauncher.INPUT.nextLine();
		}
		
    	try {
		    return TaskStatus.values()[index].toString();
		} catch (ArrayIndexOutOfBoundsException exception) {
			System.out.println ("Enter only listed value");
		}
		return null;
	}
	
	/**
	 * Gets the due date from the user in string format.
	 * 
	 * @return Date in string format.
	 */
	public String getDueDate() {
		System.out.println (" Enter Task Due Date : ");
	    return MenuLauncher.INPUT.nextLine();
	}
	
	/**
	 *  Gets the start date of the task from the user in string format.
	 *  
	 * @return Start date of the task. 
	 */
	public String getStartDate() {
		System.out.println (" Enter task start Date : ");
	    return MenuLauncher.INPUT.nextLine();
	}

	/**
	 * Prints the message.
	 * 
	 * @param message Message to be printed.
	 */
	public void printMessage(final String message) {
		System.out.println (message);
	}
	
	/**
	 * Gets the id of the assignee from the user
	 * for searching the tasks assigned to him.
	 * 
	 * @return id of the assignee.
	 */
	int getAssigneeId() {
		int assigneeId = 0;
		
		try {
		    System.out.println ("Enter the assignee id :");
		    assigneeId = MenuLauncher.INPUT.nextInt();
		    MenuLauncher.INPUT.nextLine();
		} catch (InputMismatchException exception) {
			System.out.println ("Enter only integer value");
			getAssigneeId();
		}
		return assigneeId;
	}
	
	/**
	 * Gets the list of task id from the user.
	 * 
	 * @return list of task id.
	 */
	int[] getListOfTaskId() {
		System.out.println ("Enter the total number of tasks");
		int[] taskIdList = new int[ MenuLauncher.INPUT.nextInt()];
		MenuLauncher.INPUT.nextLine();
		
		for (int initialValue = 0; initialValue < taskIdList.length; initialValue++) {
			System.out.println ("Enter the task id");
			taskIdList [initialValue] = MenuLauncher.INPUT.nextInt();
			MenuLauncher.INPUT.nextLine();			
		}
		return taskIdList;
	}
	
	/**
	 * Prints the details of task.
	 * 
	 * @param task Object of task class.
	 */
	void printDetails(final Task task) {
		if (task == null) {
			System.out.println ("No task found");
		} else {
		System.out.println ("Task details");
		System.out.println ("Task id : " + task.getTaskId());
		System.out.println ("Task name : " + task.getTaskName());
		System.out.println ("Task description : " + task.getTaskDescription());
		System.out.println ("Task start date : " + task.getTaskStartDate());
		System.out.println ("Task due date : " + task.getTaskDueDate());
		System.out.println ("Task status : " + task.getTaskStatus());
		}
	}
	
	/**
	 * Prints all the available task.
	 * 
	 * @param taskCollection List of all available tasks.
	 */
	void printAllTask(final ArrayList<Task> taskCollection) {
		if (taskCollection.isEmpty()) {
			System.out.println ("No task found");
		} else {
		    for (final Task task : taskCollection) {
		    	System.out.println ("Task details");
		    	System.out.println ("task id : " + task.getTaskId());
		    	System.out.println ("Task name is : " + task.getTaskName());
		    	System.out.println ("Task Description is : " + task.getTaskDescription());
		    	System.out.println ("Task start date is : " + task.getTaskStartDate());
		    	System.out.println ("Task due date is : " + task.getTaskDueDate());
		    	System.out.println ("Task status is : " + task.getTaskStatus());
		    }
		}
	}
}
