package com.cjanie.todoc.write.businesslogic.usecases;

import com.cjanie.todoc.read.businesslogic.usecases.TaskVO;
import com.cjanie.todoc.write.businesslogic.gateways.commands.TaskCommand;

public class AddTask {

    private TaskCommand taskCommand;

    public AddTask(TaskCommand taskCommand) {
        this.taskCommand = taskCommand;
    }

    public void handle(TaskVO task) {
        this.taskCommand.add(task);
    }
}
