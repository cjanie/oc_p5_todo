package com.cleanup.todoc.database.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.cleanup.todoc.modelpersistance.ProjectWithTasks;

@Dao
public interface ProjectWithTasksDao {

    @Transaction
    @Query("SELECT * FROM Project")
    ProjectWithTasks[] findAll();

    @Transaction
    @Query("SELECT * FROM Project WHERE id = :id")
    ProjectWithTasks findById(long id);

}
