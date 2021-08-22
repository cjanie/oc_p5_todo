package com.cjanie.todoc.write.adapters.secondary;

import com.cjanie.todoc.database.dao.TaskDao;
import com.cjanie.todoc.modelpersistance.Task;
import com.cjanie.todoc.read.businesslogic.usecases.TaskVO;
import com.cjanie.todoc.write.businesslogic.gateways.commands.TaskCommand;

public class SQLTaskCommand implements TaskCommand {

    private TaskDao taskDao;

    public SQLTaskCommand(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public void add(TaskVO task) {
        if(task != null) {
            Task t = new Task(task.getId(), task.getProjectVO().getId(), task.getName(), task.getCreationTimestamp());
            this.taskDao.create(t);
        }

    }

    @Override
    public void deleteById(long id) {
        this.taskDao.deleteById(id);
    }

}
