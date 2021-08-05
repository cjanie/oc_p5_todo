package com.cleanup.todoc.read.businesslogic.gateways.queries;

import com.cleanup.todoc.read.businesslogic.usecases.ProjectVO;

public interface ProjectQuery {

    ProjectVO retrieveById(long projectId);
    ProjectVO[] retrieveAll();
}
