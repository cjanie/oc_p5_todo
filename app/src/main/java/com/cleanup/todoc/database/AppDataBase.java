package com.cleanup.todoc.database;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import androidx.annotation.NonNull;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.modelpersistance.Project;
import com.cleanup.todoc.modelpersistance.Task;

@Database(entities = {Task.class, Project.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    // SINGLETON
    private static volatile AppDataBase INSTANCE;

    // DAO
    public abstract TaskDao taskDao();
    public abstract ProjectDao projectDao();

    // INSTANCE
    public static AppDataBase getInstance(Context context) {
        if(AppDataBase.INSTANCE == null) {
            synchronized(AppDataBase.class) {
                if(AppDataBase.INSTANCE == null) {
                    AppDataBase.INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "AppDataBase.db")
                            .addCallback(prepopulateDatabase())
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return AppDataBase.INSTANCE;
    }

    private static Callback prepopulateDatabase() {
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                Project[] projects = new Project[] {
                        new Project(1L, "Projet Tartampion", 0xFFEADAD1),
                        new Project(2L, "Projet Lucidia", 0xFFB4CDBA),
                        new Project(3L, "Projet Circus", 0xFFA3CED2),
                };

                for(Project project: projects) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", project.getId());
                    contentValues.put("name", project.getName());
                    contentValues.put("color", project.getColor());
                    db.insert("Project", OnConflictStrategy.IGNORE, contentValues);
                }
            }
        };
    }
}
