package com.cleanup.todoc.ui.injections;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.cleanup.todoc.read.businesslogic.usecases.RetrieveTasksByProject;
import com.cleanup.todoc.ui.TaskViewModel;
import com.cleanup.todoc.read.businesslogic.usecases.RetrieveProjects;
import com.cleanup.todoc.read.businesslogic.usecases.RetrieveTasks;
import com.cleanup.todoc.write.businesslogic.usecases.AddTask;
import com.cleanup.todoc.write.businesslogic.usecases.DeleteTask;

public class TaskViewModelFactory implements ViewModelProvider.Factory {

    private final RetrieveTasks retrieveTasks;

    private final RetrieveProjects retrieveProjects;

    private final AddTask addTask;

    private final DeleteTask deleteTask;

    private final RetrieveTasksByProject retrieveTasksByProject;

    public TaskViewModelFactory(
            RetrieveTasks retrieveTasks,
            RetrieveProjects retrieveProjects,
            AddTask addTask,
            DeleteTask deleteTask,
            RetrieveTasksByProject retrieveTasksByProject) {
        this.retrieveTasks = retrieveTasks;
        this.retrieveProjects = retrieveProjects;
        this.addTask = addTask;
        this.deleteTask = deleteTask;
        this.retrieveTasksByProject = retrieveTasksByProject;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(TaskViewModel.class)) {
            return (T) new TaskViewModel(
                    this.retrieveTasks,
                    this.retrieveProjects,
                    this.addTask,
                    this.deleteTask,
                    this.retrieveTasksByProject);
        }
        throw new IllegalArgumentException("TaskViewModelFactory: Unknown ViewModel class");
    }
}
