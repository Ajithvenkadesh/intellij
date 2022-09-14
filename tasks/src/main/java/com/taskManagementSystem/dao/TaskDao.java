package com.taskManagement.dao;

import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.taskManagement.model.Task;

/**
 * Create new task,deletes a task, updates task,
 * search a particular task.
 * 
 * @author Ajithvenkadesh
 * @version 1.0
 */
public class TaskDao {
	
	/**
	 * Creates new task.
	 * 
	 * @param name Name of the task.
	 * @param description Description about the task.
	 * @param status Status of the task.
	 * @param taskStartDate Start date of the task.
	 * @param taskDueDate Due date of the task.
	 * @return Success or failure message.
	 */
	public String create(final String name, final String description,
			final String status, final Date taskStartDate, final Date taskDueDate) {
		final Connector connector = new Connector();
		final java.sql.Date startDate = new java.sql.Date(taskStartDate.getTime());
        final java.sql.Date dueDate = new java.sql.Date(taskDueDate.getTime());
        final String selectSql = "SELECT task_id FROM task ORDER BY task_id DESC LIMIT 1";
		final String insertSql = "INSERT INTO task (task_name, task_description, "
				+ "task_status, task_start_date, task_due_date) VALUES "
				+ "(?, ?, ?, ?, ?)";
		
		try {
		    final PreparedStatement insertStatement = connector.connect().prepareStatement(insertSql);
		    insertStatement.setString(1, name);
		    insertStatement.setString(2, description);
		    insertStatement.setString(3, status);
		    insertStatement.setDate(4, startDate);
		    insertStatement.setDate(5, dueDate);
		    
		    if (insertStatement.executeUpdate() > 0) {
		    	final Statement selectStatement = connector.connect().createStatement();
				final ResultSet result = selectStatement.executeQuery(selectSql);
				
				while (result.next()) {
					return "Task inserted , id is " +  result.getInt(1);
				}
			}
		} catch (SQLException exception) {}
		return "Task not inserted";
	}
	
	/**
	 * Displays all tasks.
	 * 
	 * @return List of tasks.
	 */
	public ArrayList<Task> dispalay() {
		final Connector connector = new Connector();
		final ArrayList<Task> list = new ArrayList<Task>();
		final String sql = "SELECT * FROM task";
		
		try {
		    final Statement statement = connector.connect().createStatement();
		    final ResultSet result = statement.executeQuery(sql);
			
		    while (result.next()){
			    final Task task = new Task(result.getInt(1), result.getString(2), result.getString(3),
			            result.getDate(5), result.getDate(6), result.getString(4));
			    list.add(task);
			}
		} catch (SQLException exception) {}
		return list;
	}

	/**
	 * Updates a particular task record.
	 * 
	 * @param id Id of the task.
	 * @param name Name of the task.
	 * @param description Description about the task.
	 * @param status Status of the task.
	 * @param startDate Start date of the task.
	 * @param dueDate Due date of the task.
	 * @return Success or failure message
	 */
	public String update(final int id, final String name, final String description, 
			final String status, final Date startDate, final Date dueDate) {
		final java.sql.Date taskStartDate = new java.sql.Date(startDate.getTime());
		final java.sql.Date taskDueDate = new java.sql.Date(dueDate.getTime());		
		final Connector connector = new Connector();
		final String testSql = "SELECT * FROM task WHERE task_id = " + id;
		final String updateSql = "UPDATE task SET task_name=? , task_description = ?,"
				+ "task_status = ?, task_start_date = ?, task_due_date = ?,"
				+ "WHERE task_id=?";
		
		try {
			final Statement testStatement = connector.connect().createStatement();
			final ResultSet result = testStatement.executeQuery(testSql);
			
			if (result != null) {
		        final PreparedStatement updateStatement = connector.connect().prepareStatement(updateSql);
		        updateStatement.setString(1, name);
		        updateStatement.setString(2, description);
		        updateStatement.setString(3, status);
		        updateStatement.setDate(4, taskStartDate);
		        updateStatement.setDate(5, taskDueDate);
		        updateStatement.setInt(6, id);
				
		        if (updateStatement.executeUpdate() > 0) {
		            return "An existing user was updated successfully!";
		        }
			}
		} catch (SQLException exception) {}
		return "Assignee was not updated";
	}
	
	/**
	 * Deletes a particular task.
	 * 
	 * @param id Id of the task to be deleted.
	 * @return Success or failure message.
	 */
	public String delete(final int id) {
		final String testSql = "SELECT * FROM task WHERE task_id =" + id;
		final String deleteSql = "DELETE FROM task WHERE task_id=?";
		final Connector connector = new Connector();
				
		try {
			final Statement testStatement = connector.connect().createStatement();
			final ResultSet result = testStatement.executeQuery(testSql);
			
			if (result != null) {
		        final PreparedStatement deleteStatement = connector.connect().prepareStatement(deleteSql);
		        deleteStatement.setInt(1, id);
					
		        if (deleteStatement.executeUpdate() > 0) {
		            return "A user was deleted successfully!";
		        }
			}
		} catch (SQLException exception) {}
		return "User was not deleted";
	}
	
	/**
	 * Searches a particular task.
	 * 
	 * @param id Id of the task.
	 * @return Object of task.
	 */
	public Task search(final int id) {
		final String sql = "SELECT * FROM task where task_id = " + id;
		final Connector connector = new Connector();
				
		try {
		    final Statement statement = connector.connect().createStatement();
		    final ResultSet result = statement.executeQuery(sql);
			 
		    while (result.next()){
			    final Task task = new Task(result.getInt(1), result.getString(2), result.getString(3), 
		    	    	result.getDate(5), result.getDate(6), result.getString(4));
			    return task;
		    }
		} catch (SQLException exception) {}
		return null;
	}
	
    /**
     * Searches task by using status of task.
     * 
     * @param taskStatus Status of the task.
     * @return Required list of tasks.
     */
	public ArrayList<Task> searchTaskByStatus(final String taskStatus) {
		final String sql = "SELECT * FROM task where task_status =  " + "'" + taskStatus + "'" ;
		final Connector connector = new Connector();
		final ArrayList<Task> taskList = new ArrayList<Task>();
		
		try {
		    final Statement statement = connector.connect().createStatement();
		    final ResultSet result = statement.executeQuery(sql);
			
		    while (result.next()){
		        final Task task = new Task(result.getInt(1), result.getString(2), result.getString(2), 
		    	    	result.getDate(5), result.getDate(6), result.getString(4));
		        taskList.add(task);
		    }
		} catch (SQLException exception) {}
		return taskList;
	}
}
