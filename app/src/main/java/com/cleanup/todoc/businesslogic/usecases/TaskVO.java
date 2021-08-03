package com.cleanup.todoc.businesslogic.usecases;

public class TaskVO {

    private long id;

    private RetrieveProjectById retrieveProjectById;

    private ProjectVO projectVO;

    public TaskVO(long id, RetrieveProjectById retrieveProjectById) {
        this.id = id;
        this.retrieveProjectById = retrieveProjectById;
        this.projectVO = this.retrieveProjectById.handle();
    }

    public ProjectVO getProjectVO() {
        return projectVO;
    }
}
