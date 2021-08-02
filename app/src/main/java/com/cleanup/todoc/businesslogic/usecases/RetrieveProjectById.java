package com.cleanup.todoc.businesslogic.usecases;

import com.cleanup.todoc.businesslogic.gateways.queries.ProjectQuery;

public class RetrieveProjectById {

    private ProjectQuery projectQuery;
    private long projectId;

    public RetrieveProjectById(ProjectQuery projectQuery, long projectId) {
        this.projectQuery = projectQuery;
        this.projectId = projectId;
    }

    public ProjectVO handle() {
        return this.projectQuery.retrieveById(this.projectId);
    }
}
