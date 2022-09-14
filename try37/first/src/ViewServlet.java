import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		final PrintWriter out = response.getWriter();
		final AssigneeDao assigneeDao = new AssigneeDao();
		final List<Assignee> list = assigneeDao.getAllAssignee();

		out.println("<a href='index.html'>Add New Assignee</a>");
		out.println("<h1>Assignee List</h1>");
		out.print("<table border='1' width='100%'");
		out.print("<tr><th>Id</th><th>Name</th><th>Role</th><th>Edit</th><th>Delete</th></tr>");
		
		for (final Assignee assignee : list) {
			out.print("<tr><td>" + assignee.getAssigneeId() + "</td><td>" + assignee.getAssigneName() + 
					"</td><td>" + assignee.getRole() + "</td><td><a href='EditServlet?id=" 
					+ assignee.getAssigneeId() + "'>edit</a></td><td><a href='DeleteServlet?id=" 
					+ assignee.getAssigneeId() + "'>delete</a></td></tr>");
		}
		out.print("</table>");
		out.close();
	}
}
