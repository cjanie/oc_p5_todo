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
        Project project = this.projectDao.getProject(projectId).getValue();
        ProjectVO projectVO = null;
        if(project != null) {
            projectVO = new ProjectVO(project.getId());
        }
        return projectVO;
    }

    public LiveData<ProjectVO> findById(long projectId) {
        MutableLiveData<ProjectVO> mProjectVO = new MutableLiveData<>();
        mProjectVO.setValue(this.retrieveById(projectId));
        return mProjectVO;
    }
}
