package com.task;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Assignee {
	@NotNull
	private String assigneeId;

	@Size(min=1, max = 10, message = "{Size.name}")
	private String assigneeName;

	@Size(min=1, max = 10, message = "{Size.name}")
	private String role;

	public Assignee(String assigneeId, String assigneeName, String role) {
		this.assigneeId = assigneeId;
		this.assigneeName = assigneeName;
		this.role = role;
	}
}