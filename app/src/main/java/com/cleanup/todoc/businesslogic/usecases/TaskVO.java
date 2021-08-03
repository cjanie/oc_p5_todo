package com.cleanup.todoc.businesslogic.usecases;

import com.cleanup.todoc.businesslogic.gateways.queries.ProjectQuery;

public class TaskVO {

    private long id;

    private long projectId;

    private ProjectVO projectVO;

    public TaskVO(long id, ProjectQuery projectQuery, long projectId) {
        this.id = id;
        this.projectId = projectId;
        this.projectVO = new RetrieveProjectById(projectQuery, projectId).handle();
    }

    public ProjectVO getProjectVO() {
        return projectVO;
    }
}
