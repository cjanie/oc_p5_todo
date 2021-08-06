package com.cleanup.todoc.read.businesslogic.usecases;

import com.cleanup.todoc.modelpersistance.Project;
import com.cleanup.todoc.modelpersistance.Task;
import com.cleanup.todoc.read.businesslogic.gateways.queries.ProjectQuery;
import com.cleanup.todoc.read.businesslogic.gateways.queries.TaskQuery;

import java.util.ArrayList;
import java.util.List;

public class RetrieveTasksByProject {

    private TaskQuery taskQuery;

    private long projectId;

    public RetrieveTasksByProject(TaskQuery taskQuery, long projectId) {
        this.taskQuery = taskQuery;
        this.projectId = projectId;
    }

    public List<TaskVO> handle() {
        List<TaskVO> taskVOs = this.taskQuery.retrieveTasksByProject(this.projectId);
        return taskVOs;
    }
}
