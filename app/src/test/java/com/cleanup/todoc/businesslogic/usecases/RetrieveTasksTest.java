package com.cleanup.todoc.businesslogic.usecases;

import com.cleanup.todoc.adapters.secondary.InMemoryTaskQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class RetrieveTasksTest {

    private final InMemoryTaskQuery taskQuery = new InMemoryTaskQuery();
    private final TaskVO taskVO1 = new TaskVO(1l);
    private final TaskVO taskVO2 = new TaskVO(2l);


    @Test
    public void shouldRetrieveEmptyListWhenThereIsNone() {
        InMemoryTaskQuery taskQuery = new InMemoryTaskQuery();
        assert(new RetrieveTasks(taskQuery).handle()).isEmpty();
    }

    @Test
    public void shouldRetrieveTasksWhenThereAreSome() {
        this.initWithSomeTasks(Arrays.asList(new TaskVO[] {taskVO1, taskVO2}));
        this.assertRetrievedTasks(Arrays.asList(new TaskVO[] {taskVO1, taskVO2}));
    }

    private void initWithSomeTasks(List<TaskVO> taskVOs) {
        this.taskQuery.setTasks(taskVOs);
    }

    private void assertRetrievedTasks(List<TaskVO> taskVOs) {
        assert(new RetrieveTasks(this.taskQuery).handle()).equals(taskVOs);
    }
}
