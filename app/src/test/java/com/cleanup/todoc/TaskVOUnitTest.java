package com.cleanup.todoc;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.read.adapters.secondary.InMemoryProjectQuery;
import com.cleanup.todoc.read.businesslogic.usecases.ProjectVO;
import com.cleanup.todoc.read.businesslogic.usecases.RetrieveProjectById;
import com.cleanup.todoc.read.businesslogic.usecases.TaskVO;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Unit tests for tasks
 *
 * @author Gaëtan HERFRAY
 */
public class TaskVOUnitTest {

    private final InMemoryProjectQuery projectQuery = new InMemoryProjectQuery();

    private final ProjectVO projectVO1 = new ProjectVO(1l, "projet1", 10);
    private final ProjectVO projectVO2 = new ProjectVO(2l, "projet2", 20);
    private final ProjectVO projectVO3 = new ProjectVO(3l, "projet3", 30);

    private final Task task1 = new Task(1, 1, "aaa", 123);
    private final Task task2 = new Task(2, 2, "zzz", 124);
    private final Task task3 = new Task(3, 3, "hhh", 125);

    @Before
    public void setUp() {
        this.initWithSomeProjects(Arrays.asList(new ProjectVO[] {this.projectVO1, this.projectVO2, this.projectVO3}));

    }

    private void initWithSomeProjects(List<ProjectVO> projectVOs) {
        this.projectQuery.setProjectVOs(projectVOs);
    }

    private List<TaskVO> provideTaskVOs() {
        List<TaskVO> taskVOs = new ArrayList<>();
        List<Task> tasks = Arrays.asList(new Task[] {this.task1, this.task2, this.task3});
        for(Task t: tasks) {
            taskVOs.add(this.formatTaskToTaskVO(t));
        }
        return taskVOs;
    }

    private TaskVO formatTaskToTaskVO(Task task) {
        TaskVO taskVO = null;
        if(task != null) {
            RetrieveProjectById retrieveProjectById = new RetrieveProjectById(this.projectQuery, task.getProjectId());
            taskVO = new TaskVO(task.getId(), retrieveProjectById, task.getName(), task.getCreationTimestamp());
        }
        return taskVO;
    }

    @Test
    public void test_az_comparator() {

        List<TaskVO> taskVOs = this.provideTaskVOs();
        Collections.sort(taskVOs, new TaskVO.TaskAZComparator());
        assert(taskVOs.get(0).getId() == task1.getId());
        assert(taskVOs.get(1).getId() == task3.getId());
        assert(taskVOs.get(2).getId() == task2.getId());
    }
/*
    @Test
    public void test_za_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new Task.TaskZAComparator());

        assertSame(tasks.get(0), task2);
        assertSame(tasks.get(1), task3);
        assertSame(tasks.get(2), task1);
    }

    @Test
    public void test_recent_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new Task.TaskRecentComparator());

        assertSame(tasks.get(0), task3);
        assertSame(tasks.get(1), task2);
        assertSame(tasks.get(2), task1);
    }

    @Test
    public void test_old_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new Task.TaskOldComparator());

        assertSame(tasks.get(0), task1);
        assertSame(tasks.get(1), task2);
        assertSame(tasks.get(2), task3);
    }

     */
}