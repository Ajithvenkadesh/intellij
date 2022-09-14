package com.taskManagementSystem.dao;

import com.taskManagementSystem.model.Task;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Create new task,deletes a task, updates task,
 * search a particular task.
 * 
 * @author Ajithvenkadesh
 * @version 1.0
 */
public class TaskDao {
	public static final Logger LOGGER = Logger.getLogger(TaskDao.class.getName());
	
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
		final ConnectionDao connector = new ConnectionDao();
		final java.sql.Date startDate = new java.sql.Date(taskStartDate.getTime());
        final java.sql.Date dueDate = new java.sql.Date(taskDueDate.getTime());
        final String selectSql = "SELECT task_id FROM task ORDER BY task_id DESC LIMIT 1";
		final String insertSql = "INSERT INTO task (task_name, task_description, "
				+ "task_status, task_start_date, task_due_date) VALUES "
				+ "(?, ?, ?, ?, ?)";

		if (connector.getConnection() == null) {
			LOGGER.warning("Unable to open connection");
		} else {
			try (PreparedStatement insertStatement = connector.getConnection().
						prepareStatement(insertSql)) {
				insertStatement.setString(1, name);
				insertStatement.setString(2, description);
				insertStatement.setString(3, status);
				insertStatement.setDate(4, startDate);
				insertStatement.setDate(5, dueDate);

				if (insertStatement.executeUpdate() > 0) {
					try (Statement selectStatement = connector.getConnection().createStatement()) {
						final ResultSet result = selectStatement.executeQuery(selectSql);

						if (result.next()) {
							return "Task inserted , id is " + result.getInt(1);
						}
					}
				} else {
					return "Task not inserted";
				}
			} catch (SQLException exception) {
				LOGGER.warning("unable to create create connection error in connection string");
			}
		}
		return "Task not inserted";
	}
	
	/**
	 * Displays all tasks.
	 * 
	 * @return List of tasks.
	 */
	public ArrayList<Task> display() {
		final ConnectionDao connector = new ConnectionDao();
		final ArrayList<Task> list = new ArrayList<Task>();
		final String sql = "SELECT * FROM task";

		if (connector.getConnection() == null) {
			LOGGER.warning("Unable to open connection");
		} else {
			try (Statement statement = connector.getConnection().createStatement()) {
				final ResultSet result = statement.executeQuery(sql);

				while (result.next()) {
					final Task task = new Task(result.getInt(1), result.getString(2), result.getString(3),
							result.getDate(5), result.getDate(6), result.getString(4));
					list.add(task);
				}
			} catch (SQLException exception) {
				LOGGER.warning("unable to create create connection error in connection string");
			}
		}
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
		final ConnectionDao connector = new ConnectionDao();
		final String testSql = "SELECT * FROM task WHERE task_id = " + id;
		final String updateSql = "UPDATE task SET task_name=COALESCE(?,task_name) ," +
				" task_description = COALESCE(?,task_description), " +
				"task_status = COALESCE(?, task_status), task_start_date = COALESCE(?,task_start_date), " +
				"task_due_date = COALESCE(?, task_due_date) WHERE task_id=?";

		if (connector.getConnection() == null) {
			LOGGER.warning("Unable to open connection");
		} else {
			try (Statement testStatement = connector.getConnection().createStatement()) {
				final ResultSet result = testStatement.executeQuery(testSql);

				if (result != null) {
					try (PreparedStatement updateStatement = connector.getConnection()
							.prepareStatement(updateSql)) {
						updateStatement.setString(1, name);
						updateStatement.setString(2, description);
						updateStatement.setString(3, status);
						updateStatement.setDate(4, taskStartDate);
						updateStatement.setDate(5, taskDueDate);
						updateStatement.setInt(6, id);

						if (updateStatement.executeUpdate() > 0) {
							return "An existing task was updated successfully!";
						} else {
							return "task was not updated";
						}
					}
				} else {
					return "no task found";
				}
			} catch (SQLException exception) {
				LOGGER.warning("unable to create create connection error in connection string");
			}
		}
		return "Task was not updated";
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
		final ConnectionDao connector = new ConnectionDao();

		if (connector.getConnection() == null) {
			LOGGER.warning("Unable to open connection");
		} else {
			try (Statement testStatement = connector.getConnection().createStatement()) {
				final ResultSet result = testStatement.executeQuery(testSql);

				if (result != null) {
					try (PreparedStatement deleteStatement = connector.getConnection().
							prepareStatement(deleteSql)) {
						deleteStatement.setInt(1, id);

						if (deleteStatement.executeUpdate() > 0) {
							return "A task was deleted successfully!";
						} else {
							return "Task was not deleted";
						}
					}
				} else {
					return "No task was found";
				}
			} catch (SQLException exception) {
				LOGGER.warning("unable to create create connection error in connection string");
			}
		}
		return "Task was not deleted";
	}
	
	/**
	 * Searches a particular task.
	 * 
	 * @param id Id of the task.
	 * @return Model of task.
	 */
	public Task search(final int id) {
		final String sql = "SELECT * FROM task where task_id = " + id;
		final ConnectionDao connector = new ConnectionDao();

		if (connector.getConnection() == null) {
			LOGGER.warning("Unable to open connection");
		} else {
			try (Statement statement = connector.getConnection().createStatement()) {
				final ResultSet result = statement.executeQuery(sql);

				if (result.next()) {
					return new Task(result.getInt(1), result.getString(2),
							result.getString(3), result.getDate(5),
							result.getDate(6), result.getString(4));
				}
			} catch (SQLException exception) {
				LOGGER.warning("unable to create create connection error in connection string");
			}
		}
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
		final ConnectionDao connector = new ConnectionDao();
		final ArrayList<Task> taskList = new ArrayList<Task>();

		if (connector.getConnection() == null) {
			LOGGER.warning("Unable to open connection");
		} else {
			try (Statement statement = connector.getConnection().createStatement()) {
				final ResultSet result = statement.executeQuery(sql);

				while (result.next()) {
					final Task task = new Task(result.getInt(1), result.getString(2),
							result.getString(2), result.getDate(5),
							result.getDate(6), result.getString(4));
					taskList.add(task);
				}
			} catch (SQLException exception) {
				LOGGER.warning("unable to create create connection error in connection string");
			}
		}
		return taskList;
	}
}
