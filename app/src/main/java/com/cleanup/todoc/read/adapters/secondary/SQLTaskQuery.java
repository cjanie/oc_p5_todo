package com.cleanup.todoc.read.adapters.secondary;

import com.cleanup.todoc.read.businesslogic.gateways.queries.ProjectQuery;
import com.cleanup.todoc.read.businesslogic.gateways.queries.TaskQuery;
import com.cleanup.todoc.read.businesslogic.usecases.TaskVO;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.modelpersistance.Task;

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
        if(this.taskDao.findAll() != null && !this.taskDao.findAll().isEmpty()) {
            for(Task task: this.taskDao.findAll()) {
                TaskVO taskVO = new FormatTaskToTaskVO(task, this.projectQuery).format();
                taskVOs.add(taskVO);
            }
        }
        return taskVOs;
    }

    @Override
    public List<TaskVO> retrieveTasksByProject(long projectId) {
        List<TaskVO> taskVOs = new ArrayList<>();
        if(!this.taskDao.getTasksByProjectId(projectId).isEmpty()) {
            for(Task task: this.taskDao.getTasksByProjectId(projectId)) {
                TaskVO taskVO = new FormatTaskToTaskVO(task, this.projectQuery).format();
                taskVOs.add(taskVO);
            }
        }
        return taskVOs;
    }

}
