import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbcp.BasicDataSource;

public class AssigneeDao {

	public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			final String url = "jdbc:postgresql://localhost/sql";
			final String password = "123";
			final String user = "postgres";
			
			return DriverManager.getConnection(url, user, password);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		return null;
	}

	public int save(final Assignee assignee) {
		final Connection connection = getConnection();

		if (connection != null) {
			try (PreparedStatement statement = connection
					.prepareStatement("insert into assignee(assignee_name, role) values (?,?)")) {
				statement.setString(1, assignee.getAssigneName());
				statement.setString(2, assignee.getRole());

				return statement.executeUpdate();
			} catch (Exception exception) {
				System.out.println(exception.getMessage());
			}
		}
		return 0;
	}

	public int update(final Assignee assignee) {
		final Connection connection = getConnection();

		if (connection != null) {
			try (PreparedStatement statement = connection
					.prepareStatement("update assignee set assignee_name=?, role=? where assignee_id=?")) {
				statement.setString(1, assignee.getAssigneName());
				statement.setString(2, assignee.getRole());
				statement.setInt(3, assignee.getAssigneeId());

				return statement.executeUpdate();
			} catch (Exception exception) {
				System.out.println(exception.getMessage());
			}
		}
		return 0;
	}

	public int delete(final int id) {
		final Connection connection = getConnection();

		if (connection != null) {
			try (PreparedStatement statement = connection
					.prepareStatement("delete from assignee where assignee_id=?")) {
				statement.setInt(1, id);
				
				return statement.executeUpdate();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return 0;
	}

	public Assignee getAssigneeById(final int id) {
		final Assignee assignee = new Assignee();
		final Connection connection = getConnection();

		if (connection != null) {
			try (PreparedStatement statement = connection
					.prepareStatement("select * from assignee where assignee_id=?")) {
				statement.setInt(1, id);
				final ResultSet result = statement.executeQuery();

				if (result.next()) {
					assignee.setAssigneeId(id);
					assignee.setAssigneeName(result.getString(2));
					assignee.setRole(result.getString(3));
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return assignee;
	}

	public List<Assignee> getAllAssignee() {
		final List<Assignee> assigneeList = new ArrayList<Assignee>();
		final Connection connection = getConnection();

		if (connection != null) {
			try (PreparedStatement statement = connection
					.prepareStatement("select * from assignee order by assignee_id desc")) {
				final ResultSet result = statement.executeQuery();
				while (result.next()) {
					final Assignee assignee = new Assignee();
					assignee.setAssigneeId(result.getInt(1));
					assignee.setAssigneeName(result.getString(2));
					assignee.setRole(result.getString(3));
					assigneeList.add(assignee);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return assigneeList;
	}
}
