package com.system.task.dao;

import com.connection.DBConnector;
import com.system.task.model.Task;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
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
     * @param name          Name of the task.
     * @param description   Description about the task.
     * @param status        Status of the task.
     * @param taskStartDate Start date of the task.
     * @param taskDueDate   Due date of the task.
     * @return Success or failure.
     */
    public int create(final String name, final String description,
                      final String status, final Date taskStartDate, final Date taskDueDate) {
        final DBConnector connector = DBConnector.getInstance();
        final Connection connection;
        final java.sql.Date startDate = new java.sql.Date(taskStartDate.getTime());
        final java.sql.Date dueDate = new java.sql.Date(taskDueDate.getTime());
        final String selectSql = "SELECT task_id FROM task ORDER BY task_id DESC LIMIT 1";
        final String insertSql = "INSERT INTO task (task_name, task_description, "
                + "task_status, task_start_date, task_due_date) VALUES "
                + "(?, ?, ?, ?, ?)";

        if ((connection = connector.getConnection()) == null) {
            LOGGER.warning("Unable to open connection");
        } else {
            try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                insertStatement.setString(1, name);
                insertStatement.setString(2, description);
                insertStatement.setString(3, status);
                insertStatement.setDate(4, startDate);
                insertStatement.setDate(5, dueDate);

                if (insertStatement.executeUpdate() > 0) {
                    try (Statement selectStatement = connection.createStatement()) {
                        final ResultSet result = selectStatement.executeQuery(selectSql);

                        if (result.next()) {
                            return result.getInt(1);
                        }
                    }
                } else {
                    return 0;
                }
            } catch (SQLException exception) {
                LOGGER.warning("unable to create create connection error in connection string");
            }
        }
        return 0;
    }

    /**
     * Displays all tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> display() {
        final DBConnector connector = DBConnector.getInstance();
        final ArrayList<Task> list = new ArrayList<Task>();
        final Connection connection;
        final String sql = "SELECT * FROM task";

        if ((connection = connector.getConnection()) == null) {
            LOGGER.warning("Unable to open connection");
        } else {
            try (Statement statement = connection.createStatement()) {
                final ResultSet result = statement.executeQuery(sql);

                while (result.next()) {
                    final Task task = new Task(result.getInt(1), result.getString(2),
                            result.getString(3), result.getDate(5),
                            result.getDate(6), result.getString(4));
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
     * @param id          Id of the task.
     * @param name        Name of the task.
     * @param description Description about the task.
     * @param status      Status of the task.
     * @param startDate   Start date of the task.
     * @param dueDate     Due date of the task.
     * @return Success or failure.
     */
    public boolean update(final int id, final String name, final String description,
                          final String status, final Date startDate, final Date dueDate) {
        final java.sql.Date taskStartDate = new java.sql.Date(startDate.getTime());
        final java.sql.Date taskDueDate = new java.sql.Date(dueDate.getTime());
        final Connection connection;
        final DBConnector connector = DBConnector.getInstance();
        final String testSql = "SELECT * FROM task WHERE task_id = " + id;
        final String updateSql = "UPDATE task SET task_name=COALESCE(?,task_name) ," +
                " task_description = COALESCE(?,task_description), " +
                "task_status = COALESCE(?, task_status), task_start_date = COALESCE(?,task_start_date), " +
                "task_due_date = COALESCE(?, task_due_date) WHERE task_id=?";

        if ((connection = connector.getConnection()) == null) {
            LOGGER.warning("Unable to open connection");
        } else {
            try (Statement testStatement = connection.createStatement()) {
                final ResultSet result = testStatement.executeQuery(testSql);

                if (result != null) {
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                        updateStatement.setString(1, name);
                        updateStatement.setString(2, description);
                        updateStatement.setString(3, status);
                        updateStatement.setDate(4, taskStartDate);
                        updateStatement.setDate(5, taskDueDate);
                        updateStatement.setInt(6, id);

                        return updateStatement.executeUpdate() > 0;
                    }
                } else {
                    return false;
                }
            } catch (SQLException exception) {
                LOGGER.warning("unable to create create connection error in connection string");
            }
        }
        return false;
    }

    /**
     * Deletes a particular task.
     *
     * @param id Id of the task to be deleted.
     * @return Success or failure.
     */
    public boolean delete(final int id) {
        final String testSql = "SELECT * FROM task WHERE task_id =" + id;
        final String deleteSql = "DELETE FROM task WHERE task_id=?";
        final DBConnector connector = DBConnector.getInstance();
        final Connection connection;

        if ((connection = connector.getConnection()) == null) {
            LOGGER.warning("Unable to open connection");
        } else {
            try (Statement testStatement = connection.createStatement()) {
                final ResultSet result = testStatement.executeQuery(testSql);

                if (result != null) {
                    try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
                        deleteStatement.setInt(1, id);

                        return deleteStatement.executeUpdate() > 0;
                    }
                } else {
                    return false;
                }
            } catch (SQLException exception) {
                LOGGER.warning("unable to create create connection error in connection string");
            }
        }
        return false;
    }

    /**
     * Searches a particular task.
     *
     * @param id Id of the task.
     * @return Model of task.
     */
    public Task get(final int id) {
        final String sql = "SELECT * FROM task where task_id = " + id;
        final DBConnector connector = DBConnector.getInstance();
        final Connection connection;

        if ((connection = connector.getConnection()) == null) {
            LOGGER.warning("Unable to open connection");
        } else {
            try (Statement statement = connection.createStatement()) {
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
        final String sql = "SELECT * FROM task where task_status =  " + "'" + taskStatus + "'";
        final Connection connection;
        final DBConnector connector = DBConnector.getInstance();
        final ArrayList<Task> taskList = new ArrayList<Task>();

        if ((connection = connector.getConnection()) == null) {
            LOGGER.warning("Unable to open connection");
        } else {
            try (Statement statement = connection.createStatement()) {
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
