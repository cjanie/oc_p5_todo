package com.cleanup.todoc.write.businesslogic.usecases;

import com.cleanup.todoc.read.businesslogic.usecases.ProjectVO;
import com.cleanup.todoc.read.businesslogic.usecases.TaskVO;

import org.junit.Test;

public class DeleteTaskTest {

    @Test
    public void shouldDecrementListWhenTaskIsDeleted() {
        ProjectVO project1 = new ProjectVO(1l, "p1", 6);
        TaskVO task1 = new TaskVO(1l, project1, "task1", 10l);
        TaskVO task2 = new TaskVO(2l, project1, "task2", 20l);
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
