package com.taskManagementSystem.connection;

import com.taskManagementSystem.dao.TaskDao;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Provides implementation for connecting to database.
 *
 * @author Ajithvenkadesh
 * @version 1.0
 */
public class DBConnect {

    /**
     * Connects to the PostgreSQL database
     *
     * @return connection object.
     */
    public Connection getConnection() {
        Connection datbaseConnection = null;
        final ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        try (InputStream input = classloader.getResourceAsStream("JDBCSettings.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            Class.forName("org.postgresql.Driver");
            final String dbURL = properties.getProperty("db.url");
            datbaseConnection = DriverManager.getConnection(dbURL);
        } catch (SQLException exception) {
            TaskDao.LOGGER.warning("Cannot open connection change connection string");
        } catch (ClassNotFoundException exception) {
            TaskDao.LOGGER.warning("Postgresql driver not initialised properly add correct" +
                    "dependency in pom file");
        } catch (IOException exception) {
            TaskDao.LOGGER.warning("Unable to open file");
        }
        return datbaseConnection;
    }
}

