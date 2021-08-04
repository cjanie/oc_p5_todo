package com.cleanup.todoc.businesslogic.usecases;

import android.support.annotation.NonNull;

public class TaskVO {

    private long id;

    private RetrieveProjectById retrieveProjectById;

    private ProjectVO projectVO;

    @NonNull
    private String name;

    private long creationTimestamp;

    public TaskVO(long id, RetrieveProjectById retrieveProjectById, @NonNull String name, long creationTimestamp) {
        this.id = id;
        this.retrieveProjectById = retrieveProjectById;
        this.projectVO = this.retrieveProjectById.handle();
        this.name = name;
        this.creationTimestamp = creationTimestamp;
    }

    public long getId() {
        return id;
    }

    public ProjectVO getProjectVO() {
        return projectVO;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }
}
