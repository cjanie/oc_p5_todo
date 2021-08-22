package com.cleanup.todoc.read.businesslogic.usecases;

import com.cleanup.todoc.read.businesslogic.gateways.queries.TaskQuery;

import java.util.ArrayList;
import java.util.List;

public class RetrieveTasksByProject {

    private TaskQuery taskQuery;

    public RetrieveTasksByProject(TaskQuery taskQuery) {
        this.taskQuery = taskQuery;
    }

    public List<TaskVO> handle(long projectId) {
        List<TaskVO> taskVOs = this.taskQuery.retrieveTasksByProject(projectId);
        return taskVOs;
    }

}
