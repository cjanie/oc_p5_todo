package com.cjanie.todoc.write.businesslogic.usecases;

import com.cjanie.todoc.read.businesslogic.usecases.ProjectVO;
import com.cjanie.todoc.read.businesslogic.usecases.TaskVO;
import com.cjanie.todoc.write.adapters.secondary.InMemoryTaskCommand;

import org.junit.Test;

public class AddTaskTest {

    @Test
    public void shouldIncrementListWhenTaskIsAdded() {
        ProjectVO project = new ProjectVO(1l, "p", 100);
        TaskVO task = new TaskVO(2l, project, "new task", 10l);
        InMemoryTaskCommand taskCommand = new InMemoryTaskCommand();
        new AddTask(taskCommand).handle(task);
        assert(taskCommand.getTasks().size() == 1);
        new AddTask(taskCommand).handle(task);
        assert(taskCommand.getTasks().size() == 2);
    }

}
