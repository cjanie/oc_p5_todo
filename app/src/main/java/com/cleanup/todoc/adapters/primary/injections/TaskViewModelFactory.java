package com.cleanup.todoc.adapters.primary.injections;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.cleanup.todoc.adapters.primary.androidapp.controllers.TaskViewModel;
import com.cleanup.todoc.adapters.secondary.SQLTaskQuery;
import com.cleanup.todoc.businesslogic.usecases.RetrieveProjects;
import com.cleanup.todoc.businesslogic.usecases.RetrieveTasks;
import com.cleanup.todoc.repositories.TaskRepository;

public class TaskViewModelFactory implements ViewModelProvider.Factory {

    private final RetrieveTasks retrieveTasks;

    private final RetrieveProjects retrieveProjects;

    public TaskViewModelFactory(RetrieveTasks retrieveTasks, RetrieveProjects retrieveProjects) {
        this.retrieveTasks = retrieveTasks;
        this.retrieveProjects = retrieveProjects;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(TaskViewModel.class)) {
            return (T) new TaskViewModel(this.retrieveTasks, this.retrieveProjects);
        }
        throw new IllegalArgumentException("TaskViewModelFactory: Unknown ViewModel class");
    }
}
