package com.system.taskAssigner.dao;

import com.connection.DBConnector;
import com.system.task.model.Task;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Assigns task to assignee, searches task assigned to assignee.
 *
 * @author Ajithvenkadesh
 * @version 1.0
 */
public class TaskAssignerDao {
    public static final Logger LOGGER = Logger.getLogger(TaskAssignerDao.class.getName());

    /**
     * Assigns task to assignee.
     *
     * @param assigneeId Id of the assignee.
     * @param taskIdList List of task id.
     */
    public void assignTask(final int assigneeId, final int[] taskIdList) {
        for (int taskId : taskIdList) {
            final DBConnector connector = DBConnector.getInstance();
            final String sql = "UPDATE task set assignee_id = ? where task_id = ?";
            Connection connection = null;

            if ((connection = connector.getConnection()) == null) {
                LOGGER.warning("Unable to open connection");
            } else {
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, assigneeId);
                    statement.setInt(2, taskId);
                    statement.executeUpdate();
                } catch (SQLException exception) {
                    LOGGER.warning("Database error");
                }
            }
        }
    }

    /**
     * Searches task assigned to assignee using assignee id.
     *
     * @param assigneeId Id of the assignee.
     * @return List of tasks.
     */
    public ArrayList<Task> searchTaskByAssigneeId(final int assigneeId) {
        final ArrayList<Task> list = new ArrayList<Task>();
        final String sql = "SELECT * FROM task where assignee_id = " + assigneeId;
        final DBConnector connector = DBConnector.getInstance();
        Connection connection = null;

        if ((connection = connector.getConnection()) == null) {
            LOGGER.warning("unable to get connection");
        } else {
            try (Statement statement = connection.createStatement()) {
                final ResultSet result = statement.executeQuery(sql);

                while (result.next()) {
                    final Task task = new Task(result.getInt(1), result.getString(2), result.getString(3),
                            result.getDate(5), result.getDate(6), result.getString(4));
                    list.add(task);
                }
            } catch (SQLException exception) {
                LOGGER.warning("Database error");
            }
        }
        return list;
    }
}
