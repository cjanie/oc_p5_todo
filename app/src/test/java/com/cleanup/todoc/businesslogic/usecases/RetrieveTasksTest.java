package com.cleanup.todoc.businesslogic.usecases;

import com.cleanup.todoc.adapters.secondary.InMemoryProjectQuery;
import com.cleanup.todoc.adapters.secondary.InMemoryTaskQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class RetrieveTasksTest {

    private InMemoryTaskQuery taskQuery;
    private InMemoryProjectQuery projectQuery;
    private TaskVO taskVO1;
    private TaskVO taskVO2;

    @Before
    public void setUp() {
        this.taskQuery = new InMemoryTaskQuery();
        this.projectQuery = new InMemoryProjectQuery();
        this.projectQuery.setProjectVOs(Arrays.asList(new ProjectVO[] {new ProjectVO(1l), new ProjectVO(2l)}));
        this.taskVO1 = new TaskVO(1l, new RetrieveProjectById(this.projectQuery, 1l));
        this.taskVO2 = new TaskVO(2l, new RetrieveProjectById(this.projectQuery, 2l));
    }


    @Test
    public void shouldRetrieveEmptyListWhenThereIsNone() {
        InMemoryTaskQuery taskQuery = new InMemoryTaskQuery();
        assert(new RetrieveTasks(taskQuery).handle()).isEmpty();
    }

    @Test
    public void shouldRetrieveTasksWhenThereAreSome() {
        this.initWithSomeTasks(Arrays.asList(new TaskVO[] {taskVO1, taskVO2}));
        this.assertRetrievedTasks(Arrays.asList(new TaskVO[] {taskVO1, taskVO2}));
        assert(new RetrieveTasks(this.taskQuery).handle().get(0).getProjectVO().getId() == 1l);
        assert(new RetrieveTasks(this.taskQuery).handle().get(1).getProjectVO().getId() == 2l);
    }

    private void initWithSomeTasks(List<TaskVO> taskVOs) {
        this.taskQuery.setTasks(taskVOs);
    }

    private void assertRetrievedTasks(List<TaskVO> taskVOs) {
        assert(new RetrieveTasks(this.taskQuery).handle()).equals(taskVOs);
    }
}
