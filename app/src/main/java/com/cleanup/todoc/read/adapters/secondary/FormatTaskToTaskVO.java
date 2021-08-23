package com.cleanup.todoc.read.adapters.secondary;

import com.cleanup.todoc.modelpersistance.Task;
import com.cleanup.todoc.read.businesslogic.gateways.queries.ProjectQuery;
import com.cleanup.todoc.read.businesslogic.usecases.ProjectVO;
import com.cleanup.todoc.read.businesslogic.usecases.TaskVO;

public class FormatTaskToTaskVO {

    private Task task;
    private ProjectQuery projectQuery;

    public FormatTaskToTaskVO(Task task, ProjectQuery projectQuery) {
        this.task = task;
        this.projectQuery = projectQuery;
    }

    public TaskVO format() {
        TaskVO taskVO = null;
        if(task != null) {
            ProjectVO projectVO = this.projectQuery.retrieveById(task.getProjectId());
            taskVO = new TaskVO(task.getId(), projectVO, task.getName(), task.getCreationTimestamp());
        }
        return taskVO;
    }
}
