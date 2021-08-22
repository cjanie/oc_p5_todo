package com.cjanie.todoc.read.businesslogic.usecases;

import com.cjanie.todoc.read.businesslogic.gateways.queries.ProjectQuery;

public class RetrieveProjects {

    private ProjectQuery projectQuery;

    public RetrieveProjects(ProjectQuery projectQuery) {
        this.projectQuery = projectQuery;
    }

    public ProjectVO[] handle() {
        ProjectVO[] projectVOs = this.projectQuery.retrieveAll();
        return projectVOs;
    }

}
