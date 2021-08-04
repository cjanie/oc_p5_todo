package com.cleanup.todoc.adapters.secondary;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.cleanup.todoc.businesslogic.gateways.queries.ProjectQuery;
import com.cleanup.todoc.businesslogic.usecases.ProjectVO;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

public class SQLProjectQuery implements ProjectQuery {

    private ProjectDao projectDao;

    public SQLProjectQuery(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public ProjectVO retrieveById(long projectId) {
        Project project = this.projectDao.getProject(projectId);
        ProjectVO projectVO = null;
        if(project != null) {
            projectVO = new ProjectVO(project.getId(), project.getName(), project.getColor());
        }
        return projectVO;
    }

    @Override
    public ProjectVO[] retrieveAll() {
        Project[] projects = this.projectDao.getProjects();
        ProjectVO[] projectVOs = new ProjectVO[projects.length];
        for(int i=0; i<projectVOs.length; i++) {
            projectVOs[i] = new ProjectVO(projects[i].getId(), projects[i].getName(), projects[i].getColor());
        }
        return projectVOs;
    }

}
