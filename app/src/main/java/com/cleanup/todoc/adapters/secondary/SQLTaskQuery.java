package com.cleanup.todoc.adapters.secondary;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.cleanup.todoc.businesslogic.gateways.queries.ProjectQuery;
import com.cleanup.todoc.businesslogic.gateways.queries.TaskQuery;
import com.cleanup.todoc.businesslogic.usecases.RetrieveProjectById;
import com.cleanup.todoc.businesslogic.usecases.TaskVO;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.ArrayList;
import java.util.List;

public class SQLTaskQuery implements TaskQuery {

    private TaskDao taskDao;

    private ProjectQuery projectQuery;

    public SQLTaskQuery(TaskDao taskDao, ProjectQuery projectQuery) {
        this.taskDao = taskDao;
        this.projectQuery = projectQuery;
    }



    @Override
    public List<TaskVO> retrieveAll() {
        List<TaskVO> taskVOs = new ArrayList<>();
        if(this.taskDao.getAllTasks().getValue() != null && !this.taskDao.getAllTasks().getValue().isEmpty()) {
            for(Task task: this.taskDao.getAllTasks().getValue()) {
                TaskVO taskVO = new TaskVO(task.getId(), new RetrieveProjectById(this.projectQuery, task.getProjectId()));
                taskVOs.add(taskVO);
            }
        }
        return taskVOs;
    }

    public LiveData<List<TaskVO>> findAll() {
        MutableLiveData<List<TaskVO>> mTaskVOs = new MutableLiveData<>();
        mTaskVOs.setValue(this.retrieveAll());
        return mTaskVOs;
    }

}
