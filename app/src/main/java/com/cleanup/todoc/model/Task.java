package com.cleanup.todoc.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cleanup.todoc.businesslogic.usecases.TaskVO;

import java.util.Comparator;

/**
 * <p>Model for the tasks of the application.</p>
 *
 * @author Gaëtan HERFRAY
 */
@Entity(foreignKeys = @ForeignKey(entity = Project.class, parentColumns = "id", childColumns = "projectId"))
public class Task {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private long projectId;

    @NonNull
    private String name;

    private long creationTimestamp;

    // ---- CONSTRUCTORS -----
    @Ignore
    public Task(long projectId, @NonNull String name, long creationTimestamp) {
        this.projectId = projectId;
        this.name = name;
        this.creationTimestamp = creationTimestamp;
    }

    public Task(long id, long projectId, @NonNull String name, long creationTimestamp) {
        this(projectId, name, creationTimestamp);
        this.setId(id);
    }

    // ---- GETTERS AND SETTERS -----
    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    private void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getProjectId() {
        return this.projectId;
    }
/*
    @Nullable
    public Project getProject() {
        return Project.getProjectById(projectId);
    } // TODO: refactor
*/
    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    private void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public long getCreationTimestamp() {
        return this.creationTimestamp;
    }

    // ---- USE-CASES ----

    /**
     * Comparator to sort task from A to Z
     */
    public static class TaskAZComparator implements Comparator<TaskVO> {
        @Override
        public int compare(TaskVO left, TaskVO right) {
            return left.getName().compareTo(right.getName());
        }
    }

    /**
     * Comparator to sort task from Z to A
     */
    public static class TaskZAComparator implements Comparator<TaskVO> {
        @Override
        public int compare(TaskVO left, TaskVO right) {
            return right.getName().compareTo(left.getName());
        }
    }

    /**
     * Comparator to sort task from last created to first created
     */
    public static class TaskRecentComparator implements Comparator<TaskVO> {
        @Override
        public int compare(TaskVO left, TaskVO right) {
            return (int) (right.getCreationTimestamp() - left.getCreationTimestamp());
        }
    }

    /**
     * Comparator to sort task from first created to last created
     */
    public static class TaskOldComparator implements Comparator<TaskVO> {
        @Override
        public int compare(TaskVO left, TaskVO right) {
            return (int) (left.getCreationTimestamp() - right.getCreationTimestamp());
        }
    }
}
