package com.cleanup.todoc.repositories;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

public class TaskRepository {

    private TaskDao taskDao;

    public TaskRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public void addTask(Task task) {
        this.taskDao.insertTask(task);
    }

    public void deleteTask(long id) {
        this.taskDao.deleteTask(id);
    }
}
