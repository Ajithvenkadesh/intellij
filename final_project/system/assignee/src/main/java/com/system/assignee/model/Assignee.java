package com.system.assignee.model;

/**
 * Template for assignee.
 *
 * @author Ajith venkadesh
 * @version 1.0
 */
public class Assignee {
    private int assigneeId;
    private String assigneeName;
    private String role;

    /**
     * Default constructor of assignee.
     */
    public Assignee() {
        super();
    }

    /**
     * Initializes the values of the member variables.
     *
     * @param assigneeId   Id of the assignee.
     * @param assigneeName Name of the assignee.
     * @param role         Role of the assignee.
     */
    public Assignee(final int assigneeId, final String assigneeName, final String role) {
        this.assigneeId = assigneeId;
        this.assigneeName = assigneeName;
        this.role = role;
    }

    /**
     * Initializes the value of assigneeName, assignee role.
     *
     * @param assigneeName Name of the assignee.
     * @param role         Role of the assignee.
     */
    public Assignee(final String assigneeName, final String role) {
        this.assigneeName = assigneeName;
        this.role = role;
    }

    /**
     * Initialise the value of assigneeId.
     *
     * @param assigneeId Id of the assignee.
     */
    public Assignee(final int assigneeId) {
        this.assigneeId = assigneeId;
    }

    /**
     * Returns the id of the assignee.
     *
     * @return Id of the assignee.
     */
    public int getAssigneeId() {
        return assigneeId;
    }

    /**
     * Sets the id of the assignee.
     *
     * @param assigneeId Id of the assignee.
     */
    public void setAssigneeId(final int assigneeId) {
        this.assigneeId = assigneeId;
    }

    /**
     * Returns the name of the assignee.
     *
     * @return Name of the assignee.
     */
    public String getAssigneeName() {
        return assigneeName;
    }

    /**
     * Sets the name of the assignee.
     *
     * @param assigneeName Name of the assignee.
     */
    public void setAssigneeName(final String assigneeName) {
        this.assigneeName = assigneeName;
    }

    /**
     * Returns the role of the assignee.
     *
     * @return Role of the assignee.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the assignee.
     *
     * @param role Role of the assignee.
     */
    public void setRole(String role) {
        this.role = role;
    }
}
