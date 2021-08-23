package com.cleanup.todoc.read.businesslogic.usecases;

import com.cleanup.todoc.read.businesslogic.gateways.queries.ProjectQuery;

public class RetrieveProjectById {

    private ProjectQuery projectQuery;

    public RetrieveProjectById(ProjectQuery projectQuery) {
        this.projectQuery = projectQuery;
    }

    public ProjectVO handle(long projectId) {
        return this.projectQuery.retrieveById(projectId);
    }

}
