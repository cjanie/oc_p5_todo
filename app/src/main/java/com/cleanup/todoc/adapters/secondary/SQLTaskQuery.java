package com.cleanup.todoc.adapters.secondary;

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
        if(this.taskDao.getAllTasks() != null && !this.taskDao.getAllTasks().isEmpty()) {
            for(Task task: this.taskDao.getAllTasks()) {
                TaskVO taskVO = new TaskVO(task.getId(),
                        new RetrieveProjectById(this.projectQuery, task.getProjectId()),
                        task.getName(),
                        task.getCreationTimestamp());
                taskVOs.add(taskVO);
            }
        }
        return taskVOs;
    }

}
