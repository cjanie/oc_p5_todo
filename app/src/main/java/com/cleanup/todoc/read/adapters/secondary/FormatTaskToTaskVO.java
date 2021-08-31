package com.cleanup.todoc.read.adapters.secondary;

import com.cleanup.todoc.modelpersistance.Project;
import com.cleanup.todoc.modelpersistance.Task;
import com.cleanup.todoc.read.businesslogic.usecases.ProjectVO;
import com.cleanup.todoc.read.businesslogic.usecases.TaskVO;

public class FormatTaskToTaskVO {

    private Task task;
    private Project project;

    public FormatTaskToTaskVO(Task task, Project project) {
        this.task = task;
        this.project = project;
    }

    public TaskVO format() {
        TaskVO taskVO = null;
        if(task != null) {
            ProjectVO projectVO = new FormatProjectToProjectVO(this.project).format();
            taskVO = new TaskVO(task.getId(), projectVO, task.getName(), task.getCreationTimestamp());
        }
        return taskVO;
    }
}
