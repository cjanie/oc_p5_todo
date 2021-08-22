package com.cjanie.todoc.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cjanie.todoc.modelpersistance.Project;

@Dao
public interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createProject(Project project);

    @Query("SELECT * FROM Project WHERE id = :projectId")
    Project getProject(long projectId);

    @Query("SELECT * FROM Project")
    Project[] getProjects();
}
