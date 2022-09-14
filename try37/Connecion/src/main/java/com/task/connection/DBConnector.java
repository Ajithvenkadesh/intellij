package com.task.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Provides implementation for connecting to database.
 *
 * @author Ajithvenkadesh
 * @version 1.0
 */
public class DBConnector {
    public final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    private static DBConnector connector;

    /**
     * Private constructor of the class
     */
    private DBConnector() {}

    /**
     * Creates model of the DBConnector.
     *
     * @return instance of DBConnector.
     */
    public static DBConnector getInstance() {
        if (connector == null) {
            connector = new DBConnector();
            return connector;
        }
        return connector;
    }


    /**
     * Connects to the PostgreSQL database
     *
     * @return connection object.
     */
    public Connection getConnection() {
        Connection datbaseConnection = null;

        try (InputStream input = new FileInputStream("C:\\try37\\taskImplementation\\" +
                "src\\main\\resources\\JDBCSettings.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            Class.forName("org.postgresql.Driver");
            final String dbURL = properties.getProperty("db.url");
            datbaseConnection = DriverManager.getConnection(dbURL);
        } catch (SQLException exception) {
            LOGGER.warning("Cannot open connection change connection string");
        } catch (ClassNotFoundException exception) {
            LOGGER.warning("Postgresql driver not initialised properly add correct" +
                    "dependency in pom file");
        } catch (IOException exception) {
            LOGGER.warning("Unable to open file");
        }
        return datbaseConnection;
    }
}

