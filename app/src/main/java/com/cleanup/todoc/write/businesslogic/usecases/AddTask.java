package com.cleanup.todoc.write.businesslogic.usecases;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.write.businesslogic.gateways.commands.TaskCommand;

public class AddTask {

    private TaskCommand taskCommand;

    public AddTask(TaskCommand taskCommand) {
        this.taskCommand = taskCommand;
    }

    public void handle(Task task) {
        this.taskCommand.add(task);
    }
}
