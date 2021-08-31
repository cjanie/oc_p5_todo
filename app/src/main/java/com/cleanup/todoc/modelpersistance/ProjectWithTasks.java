package com.cleanup.todoc.modelpersistance;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class ProjectWithTasks {

    @Embedded
    private Project project;

    @Relation(parentColumn = "id", entityColumn = "projectId")
    private List<Task> tasks;

    public ProjectWithTasks() {
        this.tasks = new ArrayList<>();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
