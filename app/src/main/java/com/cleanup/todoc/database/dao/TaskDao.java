package com.cleanup.todoc.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task WHERE projectId = :projectId")
    List<Task> getTasksByProjectId(long projectId);

    @Query("SELECT * FROM Task")
    List<Task> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long create(Task task);

    @Query("DELETE FROM Task WHERE id = :id")
    int deleteById(long id);
}
