package com.cleanup.todoc.write.businesslogic.gateways.commands;

import com.cleanup.todoc.model.Task;

public interface TaskCommand {

    void add(Task task);
    void deleteById(long taskId);
}
