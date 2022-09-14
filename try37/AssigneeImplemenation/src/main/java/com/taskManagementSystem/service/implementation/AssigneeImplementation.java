package com.taskManagementSystem.service.implementation;

import com.taskManagementSystem.dao.AssigneeDao;
import com.taskManagementSystem.model.Assignee;
import com.taskManagementSystem.service.AssigneeService;
import java.util.ArrayList;

/**
 * Provides implementation for assignee.
 * 
 * @author Ajithvenkadesh
 * @version 1.0
 */
public class AssigneeImplementation implements AssigneeService {
	private static final AssigneeDao ASSIGNEEDAO = new AssigneeDao();
	
	/**
	 * Creates a new assignee record.
	 * 
	 * @param assignee Model of assignee.
	 * @return Success or failure message
	 */
	public String create(final Assignee assignee) {
		return ASSIGNEEDAO.create(assignee);
	}
	
	/**
	 * Deletes an existing assignee record.
	 * 
	 * @param assignee Model of the assignee.
	 * @return Success or failure message.
	 */
	public String delete(final Assignee assignee) {
		return (ASSIGNEEDAO.delete(assignee.getAssigneeId()) ? "Deleted successfully" :
					"Assignee not deleted");
		}
	
	/**
	 * Updates an existing assignee record.
	 * 
	 * @param assignee object of assignee.
	 * @return Success or failure message.
	 */
	public String update(Assignee assignee) {
		return (ASSIGNEEDAO.update(assignee.getAssigneeId(), assignee.getAssigneeName(),
				assignee.getRole())) ? "Assignee updated successfully" :
				"Assignee not updated";
	}
	
	/**
	 * Finds a particular assignee record.
	 * 
	 * @param id Id of the assignee.
	 * @return Model of assignee.
	 */
	public Assignee search(int id) {
		return ASSIGNEEDAO.search(id);
	}

	/**
	 * Displays all the assignee record.
	 *
	 * @return list of assignees.
	 */
	public ArrayList<Assignee> list() {
		return ASSIGNEEDAO.list();
	}
}
