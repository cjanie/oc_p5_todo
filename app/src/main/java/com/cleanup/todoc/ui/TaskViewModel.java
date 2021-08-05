package com.cleanup.todoc.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.cleanup.todoc.modelpersistance.Task;
import com.cleanup.todoc.read.businesslogic.usecases.ProjectVO;
import com.cleanup.todoc.read.businesslogic.usecases.RetrieveProjects;
import com.cleanup.todoc.read.businesslogic.usecases.RetrieveTasks;
import com.cleanup.todoc.read.businesslogic.usecases.TaskVO;
import com.cleanup.todoc.write.businesslogic.usecases.AddTask;
import com.cleanup.todoc.write.businesslogic.usecases.DeleteTask;

import java.util.List;

public class TaskViewModel extends ViewModel {

    private RetrieveTasks retrieveTasks;

    private RetrieveProjects retrieveProjects;

    private AddTask addTask;

    private DeleteTask deleteTask;

    public TaskViewModel(
            RetrieveTasks retrieveTasks,
            RetrieveProjects retrieveProjects,
            AddTask addTask,
            DeleteTask deleteTask) {
        this.retrieveTasks = retrieveTasks;
        this.retrieveProjects = retrieveProjects;
        this.addTask = addTask;
        this.deleteTask = deleteTask;
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

    public void addTask(Task task) {
        this.addTask.handle(task);
    }

    public void deleteTask(long id) {
        this.deleteTask.handle(id);
    }

}
