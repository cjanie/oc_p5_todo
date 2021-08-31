package com.cleanup.todoc.database.dao;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.cleanup.todoc.database.AppDataBase;
import com.cleanup.todoc.modelpersistance.Project;
import com.cleanup.todoc.modelpersistance.ProjectWithTasks;
import com.cleanup.todoc.modelpersistance.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class TaskDaoTest {

    private AppDataBase appDataBase;

    // DATA SET FOR TEST
    private static long PROJECT_ID = 1;
    private static Project PROJECT_DEMO = new Project(PROJECT_ID, "OpenClassrooms Project", 0xFFEADAD1);

    private static Task NEW_TASK_DATALAYER = new Task(PROJECT_ID, "Develop DataLayer", 1L);
    private static Task NEW_TASK_VIEW_MODEL = new Task(PROJECT_ID, "Develop ViewModel", 2L);
    private static Task NEW_TASK_REFACTOR = new Task(PROJECT_ID, "Refactor code", 3L);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();

        this.appDataBase = Room.inMemoryDatabaseBuilder(context, AppDataBase.class)
                .allowMainThreadQueries()
                .build();
    }

    @Test
    public void insertAndGetProjectById() throws InterruptedException {
        // BEFORE: Insert a new project
        this.appDataBase.projectDao().createProject(this.PROJECT_DEMO);
        // TEST
        Project project = this.appDataBase.projectDao().getProject(this.PROJECT_ID);
        assertTrue(project.getName().equals(this.PROJECT_DEMO.getName()) && project.getId() == this.PROJECT_ID);
    }

    @Test
    public void insertAndGetProjectWithTasks() {
        // BEFORE: Add demo project & demo tasks
        this.appDataBase.projectDao().createProject(this.PROJECT_DEMO);
        this.appDataBase.taskDao().create(this.NEW_TASK_DATALAYER);
        this.appDataBase.taskDao().create(this.NEW_TASK_VIEW_MODEL);
        this.appDataBase.taskDao().create(this.NEW_TASK_REFACTOR);

        // TEST
        ProjectWithTasks[] projectWithTasks = this.appDataBase.projectWithTasksDao().findAll();
        assert(projectWithTasks.length == 1);
        assert(projectWithTasks[0].getTasks().size() == 3);
    }

    @Test
    public void getTasksWhenNoTaskInserted() {
        List<Task> tasks = this.appDataBase.taskDao().getTasksByProjectId(this.PROJECT_ID);
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void insertAndGetTasks() {
        // BEFORE: Add demo project & demo tasks
        this.appDataBase.projectDao().createProject(this.PROJECT_DEMO);
        this.appDataBase.taskDao().create(this.NEW_TASK_DATALAYER);
        this.appDataBase.taskDao().create(this.NEW_TASK_VIEW_MODEL);
        this.appDataBase.taskDao().create(this.NEW_TASK_REFACTOR);

        // TEST
        List<Task> tasks = this.appDataBase.taskDao().getTasksByProjectId(this.PROJECT_ID);
        assertTrue(tasks.size() == 3);
    }

    @Test
    public void insertAndUpdateTask() throws InterruptedException {
        // BEFORE: Add demo project & demo tasks. Next, update task added & re-save it
        this.appDataBase.projectDao().createProject(this.PROJECT_DEMO);
        this.appDataBase.taskDao().create(NEW_TASK_DATALAYER);
        Task taskAdded = this.appDataBase.taskDao().getTasksByProjectId(this.PROJECT_ID).get(0);
        taskAdded.setName("develop Datalayer and Test");
        this.appDataBase.taskDao().create(taskAdded);

        // TEST
        List<Task> tasks = this.appDataBase.taskDao().getTasksByProjectId(this.PROJECT_ID);
        assertTrue(tasks.size() == 1 && tasks.get(0).getName().equals("develop Datalayer and Test"));
    }

    @Test
    public void insertAndDeleteTask() throws InterruptedException {
        // BEFORE: Add demo project & demo task. Next get the task added & delete it
        this.appDataBase.projectDao().createProject(this.PROJECT_DEMO);
        this.appDataBase.taskDao().create(this.NEW_TASK_DATALAYER);
        Task taskAdded = this.appDataBase.taskDao().getTasksByProjectId(this.PROJECT_ID).get(0);
        this.appDataBase.taskDao().deleteById(taskAdded.getId());

        // TEST
        List<Task> tasks = this.appDataBase.taskDao().getTasksByProjectId(this.PROJECT_ID);
        assertTrue(tasks.isEmpty());
    }

    @After
    public void closeDb() throws Exception {
        appDataBase.close();
    }



}
