package com.cjanie.todoc.write.businesslogic.gateways.commands;

import com.cjanie.todoc.read.businesslogic.usecases.TaskVO;

public interface TaskCommand {

    void add(TaskVO task);
    void deleteById(long taskId);
}
