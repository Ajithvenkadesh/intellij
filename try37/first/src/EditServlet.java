import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		final PrintWriter out = response.getWriter();		
		final String assigneeId = request.getParameter("id");
		final int id = Integer.parseInt(assigneeId);
		final AssigneeDao assigneeDao = new AssigneeDao();
		final Assignee assignee = assigneeDao.getAssigneeById(id);
		
		response.setContentType("text/html");
		out.println("<h1>Update Employee</h1>");
		out.print("<form action='EditServlet2' method='post'>");
		out.print("<table>");
		out.print("<tr><td></td><td><input type='hidden' name='id' value='" + 
		        assignee.getAssigneeId() + "'/></td></tr>");
		out.print("<tr><td>Name:</td><td><input type='text' name='name' value='"
		        + assignee.getAssigneName() + "'/></td></tr>");
		out.print("<tr><td>Role:</td><td><input type='text' name='role' value='" 
		        + assignee.getRole() + "'/></td></tr>");
		out.print("<tr><td colspan='2'><input type='submit' value='Edit &amp; Save '/></td></tr>");
		out.print("</table>");
		out.print("</form>");
		out.close();
	}
}
