package com.cjanie.todoc.read.businesslogic.usecases;

import com.cjanie.todoc.read.businesslogic.gateways.queries.TaskQuery;

import java.util.List;

public class RetrieveTasks {

    private final TaskQuery taskQuery;

    public RetrieveTasks(TaskQuery taskQuery) {
        this.taskQuery = taskQuery;
    }

    public List<TaskVO> handle() {
        return this.taskQuery.retrieveAll();
    }

}
