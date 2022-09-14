package com.taskManagementSystem.connection;

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
public class DBConnect {
    
    /**
     * Connects to the PostgreSQL database
     *
     * @return connection object.
     */
    public Connection getConnection() {
        Connection datbaseConnection = null;
        final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Logger LOGGER = Logger.getLogger(this.getClass().getName());

        try (InputStream input = classloader.getResourceAsStream("JDBCSettings.properties")) {
            final Properties properties = new Properties();
            properties.load(input);
            Class.forName("org.postgresql.Driver");
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl(properties.getProperty("db.url"));
            dataSource.setUsername(properties.getProperty("db.username"));
            dataSource.setPassword(properties.getProperty("db.password"));
            dataSource.setMinIdle(5);
            dataSource.setMaxIdle(10);
            dataSource.setMaxTotal(25);
            datbaseConnection = dataSource.getConnection();
        } catch (SQLException exception) {

            LOGGER.warning("Cannot open connection change connection string");
        } catch (IOException exception) {
            LOGGER.warning("Unable to open file");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return datbaseConnection;
    }
}

/*BasicDataSource dataSource = new BasicDataSource();
            Class.forName("org.postgresql.Driver");
            dataSource.setUrl("jdbc:postgresql://localhost/sql");
            dataSource.setUsername("postgres");
    		dataSource.setPassword("123");
    		dataSource.setMinIdle(5);
    		dataSource.setMaxIdle(10);
    		dataSource.setMaxTotal(25);
            connection = dataSource.getConnection();*/