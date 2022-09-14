package com.system.taskAssigner.model;

/**
 * Model for taskAssigner.
 *
 * @author Ajithvenkadesh
 * @version 1.0
 */
public class TaskAssignerModel {
    private int assigneeId;
    private int[] taskIdList;

    /**
     * Deault constructor of task assigner.
     */
    public TaskAssignerModel() {
        super();
    }

    /**
     * Intializes the values of the member variables.
     *
     * @param assigneeId Id of the assignee.
     * @param taskIdList List of task id .
     */
    public TaskAssignerModel(int assigneeId, int[] taskIdList) {
        this.assigneeId = assigneeId;
        this.taskIdList = taskIdList;
    }

    /**
     * Gets the value of the assignee id.
     *
     * @return Id of the assignee.
     */
    public int getAssigneeId() {
        return assigneeId;
    }

    /**
     * Sets the value of the assignee id.
     *
     * @param assigneeId I d of the assignee.
     */
    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    /**
     * Gets the task Id list.
     *
     * @return task id list.
     */
    public int[] getTaskIdList() {
        return taskIdList;
    }

    /**
     * Sets the list of task id.
     *
     * @param taskIdList List of task to be assigned.
     */
    public void setTaskIdList(int[] taskIdList) {
        this.taskIdList = taskIdList;
    }
}
