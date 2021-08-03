package com.cleanup.todoc.adapters.primary.androidapp.controllers;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.cleanup.todoc.businesslogic.usecases.ProjectVO;
import com.cleanup.todoc.businesslogic.usecases.RetrieveProjects;
import com.cleanup.todoc.businesslogic.usecases.RetrieveTasks;
import com.cleanup.todoc.businesslogic.usecases.TaskVO;

import java.util.List;

public class TaskViewModel extends ViewModel {

    private RetrieveTasks retrieveTasks;

    private RetrieveProjects retrieveProjects;

    public TaskViewModel(RetrieveTasks retrieveTasks, RetrieveProjects retrieveProjects) {
        this.retrieveTasks = retrieveTasks;
        this.retrieveProjects = retrieveProjects;
    }

    public LiveData<List<TaskVO>> listTasks() {
        MutableLiveData<List<TaskVO>> mTasksVO = new MutableLiveData<>();
        mTasksVO.setValue(this.retrieveTasks.handle());
        return mTasksVO;
    }

    public LiveData<ProjectVO[]> getProjects() {
        MutableLiveData<ProjectVO[]> mProjectVO = new MutableLiveData<>();
        mProjectVO.setValue(this.retrieveProjects.handle());
        return mProjectVO;
    }

}
