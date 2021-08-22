package com.cleanup.todoc.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.read.businesslogic.usecases.ProjectVO;
import com.cleanup.todoc.read.businesslogic.usecases.RetrieveProjects;
import com.cleanup.todoc.read.businesslogic.usecases.RetrieveTasks;
import com.cleanup.todoc.read.businesslogic.usecases.RetrieveTasksByProject;
import com.cleanup.todoc.read.businesslogic.usecases.TaskVO;
import com.cleanup.todoc.read.businesslogic.usecases.enums.SortMethod;
import com.cleanup.todoc.write.businesslogic.usecases.AddTask;
import com.cleanup.todoc.write.businesslogic.usecases.DeleteTask;

import java.util.Collections;
import java.util.List;

public class TaskViewModel extends ViewModel {

    private final RetrieveTasks retrieveTasks;

    private final RetrieveProjects retrieveProjects;

    private final AddTask addTask;

    private final DeleteTask deleteTask;

    private final RetrieveTasksByProject retrieveTasksByProject;

    public TaskViewModel(
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

    public void addTask(TaskVO task) {
        this.addTask.handle(task);
    }

    public void deleteTask(long id) {
        this.deleteTask.handle(id);
    }

    public List<TaskVO> switchSortMethod(List<TaskVO> tasks, SortMethod sortMethod) {
        switch (sortMethod) {
            case ALPHABETICAL:
                Collections.sort(tasks, new TaskVO.TaskAZComparator());
                break;
            case ALPHABETICAL_INVERTED:
                Collections.sort(tasks, new TaskVO.TaskZAComparator());
                break;
            case RECENT_FIRST:
                Collections.sort(tasks, new TaskVO.TaskRecentComparator());
                break;
            case OLD_FIRST:
                Collections.sort(tasks, new TaskVO.TaskOldComparator());
                break;
        }
        return tasks;
    }

    public LiveData<List<TaskVO>> searchByProject(long projectId) {
        MutableLiveData<List<TaskVO>> mtasks = new MutableLiveData<>();
        List<TaskVO> tasks = this.retrieveTasksByProject.handle(projectId);
        mtasks.setValue(tasks);
        return mtasks;
    }

}
