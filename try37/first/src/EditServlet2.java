import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditServlet2")
public class EditServlet2 extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		final PrintWriter out = response.getWriter();
		final String assigneeId = request.getParameter("id");
		final int id = Integer.parseInt(assigneeId);
		final String name = request.getParameter("name");
		final String role = request.getParameter("role");
		final AssigneeDao assigneeDao = new AssigneeDao();
		final Assignee assignee = new Assignee();
		
		assignee.setAssigneeId(id);
		assignee.setAssigneeName(name);
		assignee.setRole(role);
		
		if (assigneeDao.update(assignee) > 0) {
			response.sendRedirect("ViewServlet");
		} else {
			out.println("Sorry! unable to update record");
		}
		out.close();
	}

}
