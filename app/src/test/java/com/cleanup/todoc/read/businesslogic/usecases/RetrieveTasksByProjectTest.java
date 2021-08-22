package com.cleanup.todoc.read.businesslogic.usecases;

import com.cleanup.todoc.read.adapters.secondary.InMemoryProjectQuery;
import com.cleanup.todoc.read.adapters.secondary.InMemoryTaskQuery;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class RetrieveTasksByProjectTest {

    private InMemoryTaskQuery taskQuery;
    private InMemoryProjectQuery projectQuery;
    private TaskVO taskVO1;
    private TaskVO taskVO2;
    private TaskVO taskVO3;

    @Before
    public void setUp() {
        this.taskQuery = new InMemoryTaskQuery();
        this.projectQuery = new InMemoryProjectQuery();
        this.projectQuery.setProjectVOs(Arrays.asList(new ProjectVO[] {new ProjectVO(1l, "projet1", 10), new ProjectVO(2l, "projet2", 20)}));
        this.taskVO1 = new TaskVO(1l, this.projectQuery.retrieveById( 1l), "Name", 10l);
        this.taskVO2 = new TaskVO(2l, this.projectQuery.retrieveById( 1l),"nom", 20l);
        this.taskVO3 = new TaskVO(3l, this.projectQuery.retrieveById(2l), "task du projet 2", 30l);
    }

    private void initWithSomeTasks(List<TaskVO> taskVOs) {
        this.taskQuery.setTasks(taskVOs);
    }

    @Test
    public void shouldRetrieve1TaskIfProjectHas1Task() {
        assert(new RetrieveTasksByProject(this.taskQuery).handle(1l).size() == 0);
        this.initWithSomeTasks(Arrays.asList(new TaskVO[] {this.taskVO1}));
        assert(new RetrieveTasksByProject(this.taskQuery).handle(1l).size() == 1);
    }

    @Test
    public void shoudlRetrieve2TasksIfProjectHas2Tasks() {
        this.initWithSomeTasks(Arrays.asList(new TaskVO[] {this.taskVO1, this.taskVO2}));
        assert(new RetrieveTasksByProject(taskQuery).handle(1l).size() == 2);
        this.initWithSomeTasks(Arrays.asList(new TaskVO[] {this.taskVO1, this.taskVO2, this.taskVO3}));
        assert(new RetrieveTasksByProject(taskQuery).handle(1l).size() == 2);
    }

}
