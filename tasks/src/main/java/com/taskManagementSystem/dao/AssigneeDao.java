package com.taskManagement.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.taskManagement.model.Assignee;

/**
 * Creates, deletes, reads, updates assignee records.
 * 
 * @author Ajithvenkadesh
 * @version 1.0
 */
public class AssigneeDao {
	
	/**
	 * Inserts new assignee record into the database.
	 * 
	 * @param name Name of the assignee.
	 * @return Success or failure message.
	 */
	public String create(final String name) {
		final Connector connector = new Connector();
		final String insertSql = "INSERT INTO assignee (assignee_name) VALUES (?)";
		final String selectSql = "SELECT assignee_id FROM assignee ORDER BY assignee_id DESC LIMIT 1";
				
		try {
			final PreparedStatement insertStatement = connector.connect().prepareStatement(insertSql);
			insertStatement.setString(1, name);
									
			if (insertStatement.executeUpdate() > 0) {
				final Statement selectStatement = connector.connect().createStatement();
				final ResultSet result = selectStatement.executeQuery(selectSql);
				while (result.next()) {
					return "Assignee inserted , id is " +  result.getInt(1);
				}
			}
		} catch (SQLException exception) {}
		return "Assignee not inserted";
	}
	
	/**
	 * Updates a particular assignee record.
	 * 
	 * @param id Id of the assignee.
	 * @param name Name of the assignee.
	 * @return Success or failure message.
	 */
	public String update(final int id, final String name) {
		final String updateSql = "UPDATE assignee SET assignee_name = ? WHERE assignee_id = ?";
		final Connector connector = new Connector();
		final String testSql = "select * from assignee where assignee_id =" + id;
						
		try {
			final Statement testStatement = connector.connect().createStatement();
			final ResultSet result = testStatement.executeQuery(testSql);
			
			if (result != null) {
			    final PreparedStatement updateStatement = connector.connect().prepareStatement
			    		(updateSql);
			    updateStatement.setString(1, name);
			    updateStatement.setInt(2, id);
			
			    if (updateStatement.executeUpdate() > 0) {
			        return "An existing user was updated successfully!";
			    }
			} 
		} catch (SQLException exception) {}
		return "Assignee was not updated";
	}
	
	/**
	 * Deletes a particular record from assignee table.
	 * 
	 * @param id Id of the assignee.
	 * @return Success or failure message.
	 */
	public String delete(final int id) {
		final String updateSql = "DELETE FROM assignee WHERE assignee_id = ?";
		final String testSql = "select * from assignee where assignee_id =" + id;
		final Connector connector = new Connector();
				
		try {
			final Statement testStatement = connector.connect().createStatement();
			final ResultSet result = testStatement.executeQuery(testSql);
			
			if (result != null) {
			    final PreparedStatement deleteStatement = connector.connect().prepareStatement
			    		(updateSql);
			    deleteStatement.setInt(1, id);
						
		        if (deleteStatement.executeUpdate() > 0) {
		            return "A user was deleted successfully!";
		        }
			}
		} catch (SQLException exception) {}
		return "User was not deleted";
	}
	
	/**
	 * Searches a particular assignee record using assignee id.
	 * 
	 * @param id Id of the assignee.
	 * @return Object of assignee class.
	 */
	public Assignee search(final int id) {
		try {
		    final String sql = "SELECT * FROM assignee WHERE assignee_id = " + id;
		    final Connector connector = new Connector();
		    final Statement statement = connector.connect().createStatement();
		    final ResultSet result = statement.executeQuery(sql);
		    			
		    while (result.next()){
			    Assignee assignee = new Assignee(result.getInt(1), result.getString(2));
			    return assignee;
		    }
		} catch (SQLException exception) {}
		return null;	
	}	   
}
