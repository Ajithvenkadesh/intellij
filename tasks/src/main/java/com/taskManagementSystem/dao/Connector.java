package com.taskManagement.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import com.taskManagement.view.MenuLauncher;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Provides method for connecting to database.
 * 
 * @author Ajithvenkadesh
 * @version 1.0
 */
public class Connector {
		
	/**
	 * Connects to the PostgreSQL database
	 * 
	 * @return connection object.
	 */
	public Connection connect() {
		final Properties properties = new Properties();
		final String propertyFileLocation = "C:\\Users\\Ajithvenkadesh\\eclipse-workspace"
				+ "\\task\\src\\main\\resources\\JDBCSettings.properties";
		Connection connection = null; 
		    	
		try {
			final FileReader fileReader = new FileReader(propertyFileLocation);
		   	properties.load(fileReader);
		} catch (IOException e) {
			MenuLauncher.LOGGER.warning("Change file location");
		}
		   	
		try {
			final BasicDataSource dataSource = new BasicDataSource();
	        dataSource.setUrl(properties.getProperty("db.url"));
	        dataSource.setUsername(properties.getProperty("db.username"));
	        dataSource.setPassword(properties.getProperty("db.password"));
	        dataSource.setMinIdle(5);
	        dataSource.setMaxIdle(10);
	        dataSource.setMaxTotal(25);
	        connection = dataSource.getConnection();
		} catch (SQLException e) {
			MenuLauncher.LOGGER.severe("Change the url or db user name or db password"
					+ "in properties file");
		}
		        
		if (connection != null) {
		   	return connection;
		} else {
			return null;
		}
	}
}

