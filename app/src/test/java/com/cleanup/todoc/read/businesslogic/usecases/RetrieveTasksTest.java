package com.cleanup.todoc.read.businesslogic.usecases;

import com.cleanup.todoc.read.InMemoryProjectQuery;
import com.cleanup.todoc.read.InMemoryTaskQuery;

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
        this.projectQuery.setProjectVOs(Arrays.asList(new ProjectVO[] {new ProjectVO(1l, "projet1", 10), new ProjectVO(2l, "projet2", 20)}));
        this.taskVO1 = new TaskVO(1l, this.projectQuery.retrieveById(1l), "Name", 10l);
        this.taskVO2 = new TaskVO(2l, this.projectQuery.retrieveById(2l),"nom", 20l);
    }

    private void initWithSomeTasks(List<TaskVO> taskVOs) {
        this.taskQuery.setTasks(taskVOs);
    }

    private void assertRetrievedTasks(List<TaskVO> taskVOs) {
        assert(new RetrieveTasks(this.taskQuery).handle()).equals(taskVOs);
    }

    @Test
    public void shouldBeEmptyListWhenThereIsNone() {
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

}
