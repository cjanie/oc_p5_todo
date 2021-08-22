package com.cjanie.todoc.read.adapters.secondary;

import com.cjanie.todoc.database.dao.ProjectDao;
import com.cjanie.todoc.modelpersistance.Project;
import com.cjanie.todoc.read.businesslogic.gateways.queries.ProjectQuery;
import com.cjanie.todoc.read.businesslogic.usecases.ProjectVO;

public class SQLProjectQuery implements ProjectQuery {

    private ProjectDao projectDao;

    public SQLProjectQuery(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public ProjectVO retrieveById(long projectId) {
        Project project = this.projectDao.getProject(projectId);
        return new FormatProjectToProjectVO(project).format();
    }

    @Override
    public ProjectVO[] retrieveAll() {
        Project[] projects = this.projectDao.getProjects();
        ProjectVO[] projectVOs = new ProjectVO[projects.length];
        for(int i=0; i<projectVOs.length; i++) {
            projectVOs[i] = new FormatProjectToProjectVO(projects[i]).format();
        }
        return projectVOs;
    }

}
