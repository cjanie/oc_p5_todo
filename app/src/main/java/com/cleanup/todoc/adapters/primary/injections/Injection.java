package com.cleanup.todoc.adapters.primary.injections;

import android.content.Context;

import com.cleanup.todoc.adapters.secondary.SQLProjectQuery;
import com.cleanup.todoc.adapters.secondary.SQLTaskQuery;
import com.cleanup.todoc.businesslogic.usecases.RetrieveTasks;
import com.cleanup.todoc.database.AppDataBase;

public class Injection {

    public static SQLProjectQuery provideSQLProjectQuery(Context context) {
        AppDataBase dataBase = AppDataBase.getInstance(context);
        return new SQLProjectQuery(dataBase.projectDao());
    }

    public static SQLTaskQuery provideSQLTaskQuery(Context context) {
        AppDataBase dataBase = AppDataBase.getInstance(context);
        return new SQLTaskQuery(dataBase.taskDao(), Injection.provideSQLProjectQuery(context));
    }

    public static TaskViewModelFactory provideTaskViewModelFactory(Context context) {
        return new TaskViewModelFactory(Injection.provideSQLTaskQuery(context));
    }
}
