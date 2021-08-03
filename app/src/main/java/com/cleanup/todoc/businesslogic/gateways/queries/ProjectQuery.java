package com.cleanup.todoc.businesslogic.gateways.queries;

import com.cleanup.todoc.businesslogic.usecases.ProjectVO;
import com.cleanup.todoc.model.Project;

public interface ProjectQuery {

    ProjectVO retrieveById(long projectId);
    ProjectVO[] retrieveAll();
}
