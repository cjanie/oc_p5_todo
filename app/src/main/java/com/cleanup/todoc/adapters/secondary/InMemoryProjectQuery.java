package com.cleanup.todoc.adapters.secondary;


import com.cleanup.todoc.businesslogic.gateways.queries.ProjectQuery;
import com.cleanup.todoc.businesslogic.usecases.ProjectVO;

import java.util.ArrayList;
import java.util.List;

public class InMemoryProjectQuery implements ProjectQuery {

    private List<ProjectVO> projectVOs;

    public InMemoryProjectQuery() {
        this.projectVOs = new ArrayList<>();
    }

    @Override
    public ProjectVO retrieveById(long projectId) {
        for(ProjectVO projectVO: projectVOs) {
            if(projectVO.getId() == projectId) {
                return projectVO;
            }
        }
        return null;
    }

    public void setProjectVOs(List<ProjectVO> projectVOs) {
        this.projectVOs = projectVOs;
    }


}
