package com.cleanup.todoc.write.businesslogic.usecases;

import com.cleanup.todoc.modelpersistance.Task;
import com.cleanup.todoc.write.adapters.secondary.InMemoryTaskCommand;

import org.junit.Test;

public class AddTaskTest {


    @Test
    public void shouldIncrementListWhenTaskIsAdded() {
        Task task = new Task(2l, "new task", 10l);
        Task task2 = new Task(2l, "new task", 20l);
        InMemoryTaskCommand taskCommand = new InMemoryTaskCommand();
        new AddTask(taskCommand).handle(task);
        new AddTask(taskCommand).handle(task);
        assert(taskCommand.getTasks().size() == 2);
    }
}
