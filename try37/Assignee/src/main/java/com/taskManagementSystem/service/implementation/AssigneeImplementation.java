package com.taskManagementSystem.service.implementation;

import com.taskManagement.validation.Validator;
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
	private final AssigneeDao assigneeDao = new AssigneeDao();
	private final Validator validator = new Validator();
	
	/**
	 * Creates a new assignee record.
	 * 
	 * @param assignee Model of assignee.
	 * @return Success or failure message
	 */
	public String create(final Assignee assignee) {
		return assigneeDao.create(assignee);
	}
	
	/**
	 * Deletes an existing assignee record.
	 * 
	 * @param assignee Model of the assignee.
	 * @return Success or failure message.
	 */
	public String delete(final Assignee assignee) {
		if (validator.validateAssigneeId(assignee.getAssigneeId())) {
			return assigneeDao.delete(assignee.getAssigneeId());
		} else {
			return "Invalid assignee id";
		}
	}
	
	/**
	 * Updates an existing assignee record.
	 * 
	 * @param assignee object of assignee.
	 * @return Success or failure message.
	 */
	public String update(Assignee assignee) {
		if (validator.validateAssigneeId(assignee.getAssigneeId())) {
			return assigneeDao.update(assignee.getAssigneeId(), assignee.getAssigneeName(),
					assignee.getRole());
		} else {
			return "Invalid details";
		}
	}
	
	/**
	 * Finds a particular assignee record.
	 * 
	 * @param id Id of the assignee.
	 * @return Model of assignee.
	 */
	public Assignee search(int id) {
		if (validator.validateAssigneeId(id)) {
			return assigneeDao.search(id);
		} else {
			return null;
		}
	}

	/**
	 * Displays all the assignee record.
	 *
	 * @return list of assignees.
	 */
	public ArrayList<Assignee> list() {
		return assigneeDao.list();
	}
}
