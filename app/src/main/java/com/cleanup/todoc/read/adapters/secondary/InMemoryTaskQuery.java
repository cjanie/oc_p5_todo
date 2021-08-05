package com.cleanup.todoc.read.adapters.secondary;

import com.cleanup.todoc.read.businesslogic.gateways.queries.TaskQuery;
import com.cleanup.todoc.read.businesslogic.usecases.TaskVO;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTaskQuery implements TaskQuery {

    private List<TaskVO> taskVOs;

    public InMemoryTaskQuery() {
        this.taskVOs = new ArrayList<>();
    }

    @Override
    public List<TaskVO> retrieveAll() {
        return this.taskVOs;
    }

    public void setTasks(List<TaskVO> taskVOs) {
        this.taskVOs = taskVOs;
    }
}
