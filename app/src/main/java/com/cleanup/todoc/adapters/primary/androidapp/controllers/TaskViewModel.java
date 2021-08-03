package com.cleanup.todoc.adapters.primary.androidapp.controllers;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.cleanup.todoc.adapters.secondary.SQLTaskQuery;
import com.cleanup.todoc.businesslogic.usecases.RetrieveTasks;
import com.cleanup.todoc.businesslogic.usecases.TaskVO;

import java.util.List;

public class TaskViewModel extends ViewModel {

    private SQLTaskQuery sqlTaskQuery;

    public TaskViewModel(SQLTaskQuery sqlTaskQuery) {
        this.sqlTaskQuery = sqlTaskQuery;
    }

    public LiveData<List<TaskVO>> getAllTasks() {
        return this.sqlTaskQuery.findAll();
    }
}
