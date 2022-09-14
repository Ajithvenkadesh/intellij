package com.taskManagement.view;

import java.util.InputMismatchException;
import com.taskManagement.model.Assignee;

/**
 * Gets details about the assignee like id of assignee,
 * name of assignee from user and also it generates the
 * assignee id automatically.
 * 
 * @author Ajith venkadesh.
 * @version 1.0
 */
public class AssigneeDetails {

	/**
	 * Gets the id of the assignee from the user for searching,
	 * deleting, updating operations.
	 * 
	 * @return id of the assignee.
	 */
	public int getAssigneeId() {
		int assigneeId = 0;

		try {
			System.out.println ("Enter the id of the assignee");
			assigneeId = MenuLauncher.INPUT.nextInt();
			MenuLauncher.INPUT.nextLine();
		} catch (InputMismatchException exception) {
			System.out.println ("Only integer value is accepted enter ineger value");
			System.out.println ("Enter the id of the assignee");
			assigneeId = MenuLauncher.INPUT.nextInt();
			MenuLauncher.INPUT.nextLine();
		}
		return assigneeId;
	}

	/**
	 * Gets the name of the assignee from the user for
	 * inserting, updating operations.
	 * 
	 * @return name of the assignee.
	 */
	public String getAssigneeName() {
		System.out.println ("Enter the name of the assignee");
		return MenuLauncher.INPUT.nextLine();
	}

	/**
	 * Prints the message.
	 * 
	 * @param message Message to be printed.
	 */
	public void printMessage(final String message) {
		System.out.println (message);
	}

	/**
	 * Prints the details about the assignee.
	 * 
	 * @param assignee Object of assignee class.
	 */
	void print(Assignee assignee) {
		if (assignee == null) {
			System.out.println ("No assignee record found");
		} else {
			System.out.println ("Assignee details");
			System.out.println ("Assignee id is : " + assignee.getAssigneeId());
			System.out.println ("Assignee name is : " + assignee.getAssigneeName());
		}
	}
}
