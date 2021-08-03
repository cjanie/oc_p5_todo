package com.cleanup.todoc.adapters.primary.androidapp.controllers;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.cleanup.todoc.adapters.secondary.SQLTaskQuery;
import com.cleanup.todoc.businesslogic.usecases.RetrieveTasks;
import com.cleanup.todoc.businesslogic.usecases.TaskVO;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.TaskRepository;

import java.util.List;

public class TaskViewModel extends ViewModel {

    /*
    private SQLTaskQuery sqlTaskQuery;

    public TaskViewModel(SQLTaskQuery sqlTaskQuery) {
        this.sqlTaskQuery = sqlTaskQuery;
    }

    public LiveData<List<TaskVO>> getAllTasks() {
        return this.sqlTaskQuery.findAll();
    }

     */

    private TaskRepository taskRepository;

    public TaskViewModel(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public LiveData<List<Task>> listTasks() {
        return this.taskRepository.listTasks();
    }

    public void addTask(Task task) {
        this.taskRepository.addTask(task);
    }

    public void deleteTask(long id) {
        this.taskRepository.deleteTask(id);
    }
}
