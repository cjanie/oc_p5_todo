package com.cleanup.todoc.read.adapters.secondary;

import com.cleanup.todoc.modelpersistance.Project;
import com.cleanup.todoc.read.businesslogic.gateways.queries.ProjectQuery;
import com.cleanup.todoc.read.businesslogic.gateways.queries.TaskQuery;
import com.cleanup.todoc.read.businesslogic.usecases.ProjectVO;
import com.cleanup.todoc.read.businesslogic.usecases.RetrieveProjectById;
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
                ProjectVO projectVO = this.projectQuery.retrieveById(task.getProjectId());
                TaskVO taskVO = new TaskVO(task.getId(), projectVO, task.getName(), task.getCreationTimestamp());
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
                TaskVO taskVO = this.formatTaskToTaskVO(task);
                taskVOs.add(taskVO);
            }
        }
        return taskVOs;
    }

    private TaskVO formatTaskToTaskVO(Task task) {
        TaskVO taskVO = null;
        if(task != null) {
            ProjectVO projectVO = this.projectQuery.retrieveById(task.getProjectId());
            taskVO = new TaskVO(task.getId(), projectVO, task.getName(), task.getCreationTimestamp());
        }
        return taskVO;
    }

}
