package com.taskManagement.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

/**
 * Validates the task name, assignee name,
 * task start date, task due date.
 * 
 * @author Ajith venkadesh
 * @version 1.0
 */
public class Validator {
	/**
	 * Validates the task name
	 * 
	 * @param name Name of the task.
	 * @return validated name.
	 */
	public Boolean validateName(final String name) {
		final String regularExpression = "^[a-zA-Z\\s]{1,30}";
		final Pattern stringPattern = Pattern.compile(regularExpression);
		final Matcher nameMatcher = stringPattern.matcher(name);
						
		if (!nameMatcher.matches()) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Validates the start date of the task.
	 * 
	 * @param startDate Start date of the task.
	 * @return Validated start date.
	 * @throws ParseException if format of the date is wrong.
	 */
	public Boolean validateStartDate(final String startDate)	{
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		final String date = formatter.format(new Date());
		Date today;
		
		try {
			today = formatter.parse(date);
		} catch (ParseException e) {}
			
		if (formatDate(startDate).compareTo(today) < 0) {
			return false;
		} else {
		    return true;
		}
	}
	
	/**
	 * Validates the due date of the task.
	 * 
	 * @param dueDate Due date of the task.
	 * @return Validated due date.
	 * @throws ParseException if format of the date is wrong.
	 */
	public Boolean validateDueDate(final String dueDate) {
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		final String date = formatter.format(new Date());
		Date today;
		
		try {
			today = formatter.parse(date);
		} catch (ParseException e) {
			System.out.println ("Enter today's date manually");
			today = formatDate(input.nextLine());
		}
			
		if (formatDate(dueDate).compareTo(today) < 0) {
		   	System.out.println ("Due date cannot less than today enter new date");
		    return false;
		} else {
		    return true;
		}
	}
	
	/**
	 * Validates the id of the assignee.
	 * 
	 * @param id Id of the assignee.
	 * @return Validated id.
	 */
	public Boolean validateAssigneeId(final int id) {
		final String assigneeId = String.valueOf(id);
		final String regularExpression = "[0-9]{1,3}[:.,-]?$";
		final Pattern stringPattern = Pattern.compile(regularExpression);
		final Matcher nameMatcher = stringPattern.matcher(assigneeId);
				
		if (!nameMatcher.matches()) {
			System.out.println ("Invalid assignee id enter proper id");
			return false;
		} else {
		    return true;
		}
	}
	
	/**
	 * Validates the id of the assignee.
	 * 
	 * @param id Id of the assignee.
	 * @return Validated id.
	 */
	public boolean validateTaskId(final int id) {
		final String taskId = String.valueOf(id);
		final String regularExpression = "[0-9]{1,3}[:.,-]?$";
		final Pattern stringPattern = Pattern.compile(regularExpression);
		final Matcher nameMatcher = stringPattern.matcher(taskId);
				
		if (!nameMatcher.matches()) {
			System.out.println ("Invalid task id enter proper id");
			return false;
		} else {
		    return true;
		}
	}
	
	/**
	 * Generates date from string format.
	 * 
	 * @param intermediateDate Date in string format.
	 * @return Formatted date.
	 */
	private Date formatDate(final String intermediateDate) {
		try {
		    final SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			return formatDate.parse(intermediateDate);
		} catch (ParseException e) {
			System.out.println ("You have entered wrong date format enter date in yyyy-MM-dd format");
			return formatDate(input.nextLine());
		}
	}
}
