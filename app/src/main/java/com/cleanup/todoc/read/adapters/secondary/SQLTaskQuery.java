package com.cleanup.todoc.read.adapters.secondary;

import com.cleanup.todoc.database.dao.ProjectWithTasksDao;
import com.cleanup.todoc.modelpersistance.ProjectWithTasks;
import com.cleanup.todoc.modelpersistance.Task;
import com.cleanup.todoc.read.businesslogic.gateways.queries.TaskQuery;
import com.cleanup.todoc.read.businesslogic.usecases.ProjectVO;
import com.cleanup.todoc.read.businesslogic.usecases.TaskVO;

import java.util.ArrayList;
import java.util.List;

public class SQLTaskQuery implements TaskQuery {

    private ProjectWithTasksDao projectWithTasksDao;

    public SQLTaskQuery(ProjectWithTasksDao projectWithTasksDao) {
        this.projectWithTasksDao = projectWithTasksDao;
    }

    @Override
    public List<TaskVO> retrieveAll() {
        List<TaskVO> taskVOs = new ArrayList<>();
        ProjectWithTasks[] projectsWithTasks = this.projectWithTasksDao.findAll();
        if(projectsWithTasks.length > 0) {
            for(ProjectWithTasks projectWithTasks: projectsWithTasks) {
                for(Task task: projectWithTasks.getTasks()) {
                    TaskVO taskVO =
                            new FormatTaskToTaskVO(task, projectWithTasks.getProject()).format();
                    taskVOs.add(taskVO);
                }
            }
        }
        return taskVOs;
    }

    @Override
    public List<TaskVO> retrieveTasksByProject(long projectId) {
        List<TaskVO> taskVOs = new ArrayList<>();
        ProjectWithTasks projectWithTasks = this.projectWithTasksDao.findById(projectId);
        if(projectWithTasks != null) {
            if(!projectWithTasks.getTasks().isEmpty()) {
                for(Task task: projectWithTasks.getTasks()) {
                    TaskVO taskVO = new FormatTaskToTaskVO(task, projectWithTasks.getProject()).format();
                    taskVOs.add(taskVO);
                }
            }
        }
        return taskVOs;
    }
}
