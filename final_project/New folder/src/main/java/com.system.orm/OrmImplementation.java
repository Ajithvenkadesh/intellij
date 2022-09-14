package com.system.orm;

import com.connection.DBConnector;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import com.system.assignee.model.Assignee;
import com.system.task.model.Task;
import java.util.logging.Logger;

public class OrmImplementation {
    public static final Logger LOGGER = Logger.getLogger("Logger");

    public ResultSet getListQuery(String tableName) {
        return getResult("select * from " + tableName);
    }

    public ResultSet getSearchQuery(String primaryKey, String tableName, int id) {
        return getResult("select * from " + tableName + " where " + primaryKey + " = " + id);
    }

    public boolean deleteQuery(String primaryKey, String tableName, int id) {
        return delete("DELETE FROM " + tableName + " WHERE " + primaryKey + " = " + id);
    }

    public ResultSet getResult(String sql) {
        System.out.println(sql);
        final Connection connection = DBConnector.getInstance().getConnection();

        if (!(connection == null)) {
            try {
                Statement statement = connection.createStatement();
                return statement.executeQuery(sql);
            } catch (SQLException exception) {
                LOGGER.warning("unable to create create connection error in connection string");
            }
        }
        return null;
    }

    public boolean delete(String sql) {
        final Connection connection = DBConnector.getInstance().getConnection();

        try (Statement deleteStatement = connection.createStatement()) {

            return deleteStatement.executeUpdate(sql) > 0;
        } catch (SQLException exception) {
            LOGGER.warning("unable to create create connection error in connection string");
        }
        return false;
    }


    public <T> boolean createQuerry(ArrayList<String> e, T t, String tableName) {
        System.out.println("aaaaaaa");
        System.out.println(e.toString());
        System.out.println(t instanceof Assignee);
        String string = null;

        StringJoiner columnsString = new StringJoiner(",");

        for (String name : e) {
            columnsString.add(name);
        }

        if (t instanceof Assignee) {
            Assignee assignee = (Assignee) t;

            string = "insert into " + tableName + " ( " + columnsString + " ) values ('" +
                    assignee.getAssigneeName() + "', '" + assignee.getRole() + "')";
            System.out.println(string);
        }
        if (t instanceof Task) {
            Task task = (Task) t;
            final java.sql.Date startDate = new java.sql.Date(task.getTaskStartDate().getTime());
            final java.sql.Date dueDate = new java.sql.Date(task.getTaskDueDate().getTime());

            string = "insert into " + tableName + " ( " + columnsString + " ) values ('" +
                    task.getTaskName() + "', '" + task.getTaskDescription() + "', '" +
                    task.getTaskStatus() + "', '" + startDate + "', '" + dueDate+ "')";
            System.out.println(string);
        }

        final Connection connection = DBConnector.getInstance().getConnection();
        try (Statement deleteStatement = connection.createStatement()) {

            return deleteStatement.executeUpdate(string) > 0;
        } catch (SQLException exception) {
            LOGGER.warning("unable to create create connection error in connection string");
        }
        return false;
    }

    public <T> boolean update(T t, ArrayList<String> e, String tableName, String primaryKey) {
        StringJoiner columnsString = new StringJoiner(" = ? ,");

        for (String name : e) {
            columnsString.add(name);
        }

        Assignee assignee = (Assignee) t;

        String s = "update " + tableName + " set " + columnsString + " = ? where " + primaryKey + " = ?";
        System.out.println(s);
        final Connection connection = DBConnector.getInstance().getConnection();
        try (PreparedStatement updateStatement = connection.prepareStatement(s)) {
            updateStatement.setString(1, assignee.getAssigneeName());
            updateStatement.setString(2, assignee.getRole());
            updateStatement.setInt(3, assignee.getAssigneeId());

            return updateStatement.executeUpdate() > 0;

        } catch (SQLException exception) {
            LOGGER.warning(exception.getMessage());
        }
       return false;

    }
}
