package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskRepository {

    private TaskDao taskDao;

    public TaskRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public LiveData<List<Task>> listTasks() {
        return this.taskDao.getAllTasks();
    }

    public void addTask(Task task) {
        this.taskDao.insertTask(task);
    }

    public void deleteTask(long id) {
        this.taskDao.deleteTask(id);
    }
}
