package com.cleanup.todoc.write.businesslogic.usecases;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.write.adapters.secondary.InMemoryTaskCommand;

import org.junit.Test;

import java.util.Arrays;

public class DeleteTaskTest {

    @Test
    public void shouldDecrementListWhenTaskIsDeleted() {
        Task task1 = new Task(1l, 1l, "task1", 10l);
        Task task2 = new Task(2l, 1l, "task2", 20l);
        InMemoryTaskCommand taskCommand = new InMemoryTaskCommand();
        new AddTask(taskCommand).handle(task1);
        new AddTask(taskCommand).handle(task2);
        assert(taskCommand.getTasks().size() == 2);

        new DeleteTask(taskCommand).handle(1l);
        assert(taskCommand.getTasks().size() == 1);
        new DeleteTask(taskCommand).handle(2l);
        assert(taskCommand.getTasks().isEmpty());
    }
}
