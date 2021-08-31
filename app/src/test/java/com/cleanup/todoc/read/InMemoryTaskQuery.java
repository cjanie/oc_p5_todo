package com.cleanup.todoc.read;

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

    @Override
    public List<TaskVO> retrieveTasksByProject(long projectId) {
        // TODO
        List<TaskVO> taskVOsFound = new ArrayList<>();
        if(!this.retrieveAll().isEmpty()) {
            for(TaskVO t: this.retrieveAll()) {
                if(t.getProjectVO().getId() == projectId) {
                    taskVOsFound.add(t);
                };
            }
        }
        return taskVOsFound;
    }

    public void setTasks(List<TaskVO> taskVOs) {
        this.taskVOs = taskVOs;
    }
}
