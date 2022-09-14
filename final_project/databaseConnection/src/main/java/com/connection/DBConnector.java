package com.connection;

import org.apache.commons.dbcp2.BasicDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
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
        //final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        final Properties properties = new Properties();
        BasicDataSource dataSource = new BasicDataSource();
        final Logger logger = Logger.getLogger(this.getClass().getName());
        Connection connection = null;

        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream("JDBCSettings.properties")) {
            properties.load(input);
            Class.forName("org.postgresql.Driver");
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl(properties.getProperty("db.url"));
            dataSource.setUsername(properties.getProperty("db.username"));
            dataSource.setPassword(properties.getProperty("db.password"));
            dataSource.setMinIdle(5);
            dataSource.setMaxIdle(10);
            dataSource.setMaxTotal(25);
            connection = dataSource.getConnection();
        } catch (SQLException exception) {
           logger.warning("Cannot open connection change connection string");
        } catch (IOException exception) {
            logger.warning("Unable to open file");
        } catch (ClassNotFoundException exception) {
            logger.warning("Change dependency in postgresql dependency");
            System.out.println(exception.getMessage());
        }
        return connection;
    }
}

