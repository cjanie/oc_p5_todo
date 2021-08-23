package com.cleanup.todoc.write.businesslogic.usecases;

import com.cleanup.todoc.write.businesslogic.gateways.commands.TaskCommand;

public class DeleteTask {

    private TaskCommand taskCommand;

    public DeleteTask(TaskCommand taskCommand) {
        this.taskCommand = taskCommand;
    }

    public void handle(long id) {
        this.taskCommand.deleteById(id);
    }
}
