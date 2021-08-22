package com.cleanup.todoc.read.adapters.secondary;

import com.cleanup.todoc.read.businesslogic.gateways.queries.ProjectQuery;
import com.cleanup.todoc.read.businesslogic.usecases.ProjectVO;

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

    @Override
    public ProjectVO[] retrieveAll() {
        ProjectVO[] projectVOs = new ProjectVO[this.projectVOs.size()];
        return this.projectVOs.toArray(projectVOs);
    }

    public void setProjectVOs(List<ProjectVO> projectVOs) {
        this.projectVOs = projectVOs;
    }


}
