import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String assigneeId = request.getParameter("id");
		final int id = Integer.parseInt(assigneeId);
		final AssigneeDao assigneeDao = new AssigneeDao();
		
		assigneeDao.delete(id);
		response.sendRedirect("ViewServlet");
	}
}
