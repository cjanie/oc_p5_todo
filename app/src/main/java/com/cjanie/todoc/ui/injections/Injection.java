package com.cjanie.todoc.ui.injections;

import android.content.Context;

import com.cjanie.todoc.database.AppDataBase;
import com.cjanie.todoc.read.businesslogic.usecases.RetrieveTasks;
import com.cjanie.todoc.write.adapters.secondary.SQLTaskCommand;
import com.cjanie.todoc.write.businesslogic.usecases.AddTask;
import com.cjanie.todoc.write.businesslogic.usecases.DeleteTask;
import com.cjanie.todoc.read.adapters.secondary.SQLProjectQuery;
import com.cjanie.todoc.read.adapters.secondary.SQLTaskQuery;
import com.cjanie.todoc.read.businesslogic.usecases.RetrieveProjects;
import com.cjanie.todoc.read.businesslogic.usecases.RetrieveTasksByProject;

public class Injection {

    public static SQLProjectQuery provideSQLProjectQuery(Context context) {
        AppDataBase dataBase = AppDataBase.getInstance(context);
        return new SQLProjectQuery(dataBase.projectDao());
    }

    public static SQLTaskQuery provideSQLTaskQuery(Context context) {
        AppDataBase dataBase = AppDataBase.getInstance(context);
        return new SQLTaskQuery(dataBase.taskDao(), Injection.provideSQLProjectQuery(context));
    }

    public static SQLTaskCommand provideSQLTaskCommand(Context context) {
        AppDataBase dataBase = AppDataBase.getInstance(context);
        return new SQLTaskCommand(dataBase.taskDao());
    }

    public static RetrieveTasks provideRetrieveTasks(Context context) {
        return new RetrieveTasks(Injection.provideSQLTaskQuery(context));
    }

    public static RetrieveProjects provideRetrieveProjects(Context context) {
        return new RetrieveProjects(Injection.provideSQLProjectQuery(context));
    }

    public static AddTask provideAddTask(Context context) {
        return new AddTask(provideSQLTaskCommand(context));
    }

    public static DeleteTask provideDeleteTask(Context context) {
        return new DeleteTask(provideSQLTaskCommand(context));
    }

    public static RetrieveTasksByProject provideRetrieveTaskByProject(Context context) {
        return new RetrieveTasksByProject(Injection.provideSQLTaskQuery(context));
    }

    public static TaskViewModelFactory provideTaskViewModelFactory(Context context) {
        return new TaskViewModelFactory(
                Injection.provideRetrieveTasks(context),
                Injection.provideRetrieveProjects(context),
                Injection.provideAddTask(context),
                Injection.provideDeleteTask(context),
                Injection.provideRetrieveTaskByProject(context)
        );
    }

}
