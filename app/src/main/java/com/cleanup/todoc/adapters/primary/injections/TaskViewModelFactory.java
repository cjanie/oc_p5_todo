package com.cleanup.todoc.adapters.primary.injections;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.cleanup.todoc.adapters.primary.androidapp.controllers.TaskViewModel;
import com.cleanup.todoc.adapters.secondary.SQLTaskQuery;
import com.cleanup.todoc.businesslogic.usecases.RetrieveTasks;

public class TaskViewModelFactory implements ViewModelProvider.Factory {

    private final SQLTaskQuery sqlTaskQuery;

    public TaskViewModelFactory(SQLTaskQuery sqlTaskQuery) {
        this.sqlTaskQuery = sqlTaskQuery;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(TaskViewModel.class)) {
            return (T) new TaskViewModel(this.sqlTaskQuery);
        }
        throw new IllegalArgumentException("TaskViewModelFactory: Unknown ViewModel class");
    }
}
