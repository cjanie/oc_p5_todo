package com.cjanie.todoc.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cjanie.todoc.modelpersistance.Task;

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
