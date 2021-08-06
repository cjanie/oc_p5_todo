package com.cleanup.todoc.write.businesslogic.gateways.commands;

import com.cleanup.todoc.modelpersistance.Task;
import com.cleanup.todoc.read.businesslogic.usecases.TaskVO;

public interface TaskCommand {

    void add(TaskVO task);
    void deleteById(long taskId);
}
