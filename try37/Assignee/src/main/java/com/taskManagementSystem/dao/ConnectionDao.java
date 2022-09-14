package com.taskManagementSystem.dao;

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
public class ConnectionDao {
		
	/**
	 * Connects to the PostgreSQL database
	 *
	 * @return connection object.
	 */
	public Connection getConnection() {
		final Logger logger = Logger.getLogger(ConnectionDao.class.getName());
		Connection datbaseConnection = null;

		try (InputStream input = new FileInputStream("C:\\try37\\taskImplementation\\" +
				"src\\main\\resources\\JDBCSettings.properties")) {
			Properties properties = new Properties();
			properties.load(input);
			Class.forName("org.postgresql.Driver");
			final String dbURL = properties.getProperty("db.url");
			datbaseConnection = DriverManager.getConnection(dbURL);
		} catch (SQLException exception) {
			logger.warning("Cannot open connection change connection string");
		} catch (ClassNotFoundException exception) {
			logger.warning("Postgresql driver not initialised properly add correct" +
					"dependency in pom file");
		} catch (IOException exception) {
			logger.warning("Unable to open file");
		}
		return datbaseConnection;
	}
}

