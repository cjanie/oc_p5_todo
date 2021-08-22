package com.cjanie.todoc.read.businesslogic.gateways.queries;

import com.cjanie.todoc.read.businesslogic.usecases.ProjectVO;

public interface ProjectQuery {

    ProjectVO retrieveById(long projectId);
    ProjectVO[] retrieveAll();
}
