package com.cjanie.todoc.modelpersistance;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

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

}
