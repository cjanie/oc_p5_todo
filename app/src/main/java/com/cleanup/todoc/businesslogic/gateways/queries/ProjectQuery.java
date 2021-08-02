package com.cleanup.todoc.businesslogic.gateways.queries;

import com.cleanup.todoc.businesslogic.usecases.ProjectVO;

public interface ProjectQuery {

    ProjectVO retrieveById(long projectId);
}
