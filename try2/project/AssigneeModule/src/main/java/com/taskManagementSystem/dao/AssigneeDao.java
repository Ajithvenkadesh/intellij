package com.taskManagementSystem.dao;

import com.taskManagementSystem.model.Assignee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Creates, deletes, reads, updates assignee records.
 * 
 * @author Ajithvenkadesh
 * @version 1.0
 */
public class AssigneeDao {
	public static final Logger LOGGER = Logger.getLogger(AssigneeDao.class.getName());

	/**
	 * Inserts new assignee record into the database.
	 * 
	 * @param assignee Object of the assignee.
	 * @return Success or failure message.
	 */
	public String create(final Assignee assignee) {
		final ConnectionDao connector = new ConnectionDao();
		final String insertSql = "INSERT INTO assignee (assignee_name, role) VALUES (?,?)";
		final String selectSql = "SELECT assignee_id FROM assignee ORDER BY assignee_id DESC LIMIT 1";

		if (connector.getConnection() == null) {
			LOGGER.warning("Unable to open connection");
		} else {
			try (PreparedStatement insertStatement = connector.getConnection().
					prepareStatement(insertSql) ) {
				if ((assignee.getAssigneeName() == null) || (assignee.getRole() == null)) {
					LOGGER.warning("assginee name or assignee role is null");
					return "assignee not created";
				} else {
					insertStatement.setString(1, assignee.getAssigneeName());
					insertStatement.setString(2, assignee.getRole());

					if (insertStatement.executeUpdate() > 0) {
						try (Statement selectStatement = connector.getConnection().createStatement()) {
							final ResultSet result = selectStatement.executeQuery(selectSql);

							if (result.next()) {
								return "Assignee inserted , id is " + result.getInt(1);
							}
						}
					} else {
						return "Assignee not created";
					}
				}
			} catch (SQLException exception) {
				LOGGER.warning("unable to create create connection error in connection string");
			}
		}
		return "Assignee not inserted";
	}
	
	/**
	 * Updates a particular assignee record.
	 * 
	 * @param id Id of the assignee.
	 * @param name Name of the assignee.
	 * @return Success or failure message.
	 */
	public String update(final int id, final String name, final String role) {
		final String updateSql = "UPDATE assignee SET assignee_name = COALESCE(?,assignee_name), role = COALESCE(?,role) WHERE assignee_id = ?";
		final ConnectionDao connector = new ConnectionDao();
		final String selectSql = "select * from assignee where assignee_id =" + id;

		if (connector.getConnection() == null) {
			LOGGER.warning("Unable to open connection");
		} else {
			try (Statement testStatement = connector.getConnection().createStatement()) {
				final ResultSet result = testStatement.executeQuery(selectSql);

				if (result != null) {
					try (PreparedStatement updateStatement = connector.getConnection().prepareStatement
							(updateSql)) {
						updateStatement.setString(1, name);
						updateStatement.setString(2, role);
						updateStatement.setInt(3, id);

						if (updateStatement.executeUpdate() > 0) {
							return "An existing user was updated successfully!";
						}
					}
				} else {
					return "Assignee was not found";
				}
			} catch (SQLException exception) {
				LOGGER.warning("unable to create create connection error in connection string");
			}
		}
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
		final ConnectionDao connector = new ConnectionDao();

		if (connector.getConnection() == null) {
			LOGGER.warning("Unable to open connection");
		} else {
			try (Statement testStatement = connector.getConnection().createStatement()) {
				final ResultSet result = testStatement.executeQuery(testSql);

				if (result != null) {
					try (PreparedStatement deleteStatement = connector.getConnection().prepareStatement
							(updateSql)) {
						deleteStatement.setInt(1, id);

						if (deleteStatement.executeUpdate() > 0) {
							return "A user was deleted successfully!";
						}
					}
				} else {
					return "Assignee not found";
				}
			} catch (SQLException exception) {
				LOGGER.warning("unable to create create connection error in connection string");
			}
		}
		    return "User was not deleted";
	}
	
	/**
	 * Searches a particular assignee record using assignee id.
	 * 
	 * @param id Id of the assignee.
	 * @return Object of assignee class.
	 */
	public Assignee search(final int id) {
		final String sql = "SELECT * FROM assignee WHERE assignee_id = " + id;
		final ConnectionDao connector = new ConnectionDao();

		if (connector.getConnection() == null) {
			LOGGER.warning("Unable to open connection");
		} else {
			try (Statement statement = connector.getConnection().createStatement()) {
				final ResultSet result = statement.executeQuery(sql);

				if (result.next()) {
					return new Assignee(result.getInt(1), result.getString(2),
							result.getString(3));
				}
			} catch (SQLException exception) {
				LOGGER.warning("unable to create create connection error in connection string");
			}
		}
		   return null;
	}

	/**
	 * Displays all the assignees.
	 *
	 * @return List of assignees.
	 */
	public ArrayList<Assignee> list() {
		final ConnectionDao connector = new ConnectionDao();
		final ArrayList<Assignee> list = new ArrayList<>();
		final String sql = "SELECT * FROM assignee";

		if (connector.getConnection() == null) {
			LOGGER.warning("Unable to open connection");
		} else {
			try (Statement statement = connector.getConnection().createStatement()) {
				final ResultSet result = statement.executeQuery(sql);

				while (result.next()) {
					final Assignee assignee = new Assignee(result.getInt(1),
							result.getString(2), result.getString(3));
					list.add(assignee);
				}
			} catch (SQLException exception) {
				LOGGER.warning("unable to create create connection error in connection string");
			}
		}
		return list;
	}
}
