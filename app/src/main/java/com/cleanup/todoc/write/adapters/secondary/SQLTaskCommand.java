package com.cleanup.todoc.write.adapters.secondary;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.modelpersistance.Task;
import com.cleanup.todoc.write.businesslogic.gateways.commands.TaskCommand;

public class SQLTaskCommand implements TaskCommand {

    private TaskDao taskDao;

    public SQLTaskCommand(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public void add(Task task) {
        this.taskDao.create(task);
    }

    @Override
    public void deleteById(long id) {
        this.taskDao.deleteById(id);
    }
}
