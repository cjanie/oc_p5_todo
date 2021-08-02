package com.cleanup.todoc.database.dao;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;

import com.cleanup.todoc.LiveDataTestUtil;
import com.cleanup.todoc.database.AppDataBase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

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
        this.appDataBase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), AppDataBase.class)
                .allowMainThreadQueries()
                .build();
    }

    @Test
    public void insertAndGetProject() throws InterruptedException {
        // BEFORE: Insert a new project
        this.appDataBase.projectDao().createProject(this.PROJECT_DEMO);
        // TEST
        Project project = LiveDataTestUtil.getValue(this.appDataBase.projectDao().getProject(this.PROJECT_ID));
        assertTrue(project.getName().equals(this.PROJECT_DEMO.getName()) && project.getId() == this.PROJECT_ID);
    }

    @Test
    public void getTasksWhenNoTaskInserted() throws InterruptedException {
        List<Task> tasks = LiveDataTestUtil.getValue(this.appDataBase.taskDao().getTasks(this.PROJECT_ID));
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void insertAndGetTasks() throws InterruptedException {
        // BEFORE: Add demo project & demo tasks
        this.appDataBase.projectDao().createProject(this.PROJECT_DEMO);
        this.appDataBase.taskDao().insertTask(this.NEW_TASK_DATALAYER);
        this.appDataBase.taskDao().insertTask(this.NEW_TASK_VIEW_MODEL);
        this.appDataBase.taskDao().insertTask(this.NEW_TASK_REFACTOR);

        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.appDataBase.taskDao().getTasks(this.PROJECT_ID));
        assertTrue(tasks.size() == 3);
    }

    @Test
    public void insertAndUpdateTask() throws InterruptedException {
        // BEFORE: Add demo project & demo tasks. Next, update task added & re-save it
        this.appDataBase.projectDao().createProject(this.PROJECT_DEMO);
        this.appDataBase.taskDao().insertTask(NEW_TASK_DATALAYER);
        Task taskAdded = LiveDataTestUtil.getValue(this.appDataBase.taskDao().getTasks(this.PROJECT_ID)).get(0);
        taskAdded.setName("develop Datalayer and Test");
        this.appDataBase.taskDao().updateTask(taskAdded);

        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.appDataBase.taskDao().getTasks(this.PROJECT_ID));
        assertTrue(tasks.size() == 1 && tasks.get(0).getName().equals("develop Datalayer and Test"));
    }

    @Test
    public void insertAndDeleteTask() throws InterruptedException {
        // BEFORE: Add demo project & demo task. Next get the task added & delete it
        this.appDataBase.projectDao().createProject(this.PROJECT_DEMO);
        this.appDataBase.taskDao().insertTask(this.NEW_TASK_DATALAYER);
        Task taskAdded = LiveDataTestUtil.getValue(this.appDataBase.taskDao().getTasks(this.PROJECT_ID)).get(0);
        this.appDataBase.taskDao().deleteTask(taskAdded.getId());

        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.appDataBase.taskDao().getTasks(this.PROJECT_ID));
        assertTrue(tasks.isEmpty());
    }

    @After
    public void closeDb() throws Exception {
        appDataBase.close();
    }

}
