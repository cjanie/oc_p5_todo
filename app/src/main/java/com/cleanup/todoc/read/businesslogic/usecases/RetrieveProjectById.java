package com.cleanup.todoc.read.businesslogic.usecases;

import com.cleanup.todoc.read.businesslogic.gateways.queries.ProjectQuery;

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
