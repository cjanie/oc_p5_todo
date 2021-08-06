package com.cleanup.todoc.read.businesslogic.usecases;

import com.cleanup.todoc.modelpersistance.Task;
import com.cleanup.todoc.read.adapters.secondary.FormatTaskToTaskVO;
import com.cleanup.todoc.read.adapters.secondary.InMemoryProjectQuery;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    @Before
    public void setUp() {
        this.initWithSomeProjects(Arrays.asList(new ProjectVO[] {this.projectVO1, this.projectVO2, this.projectVO3}));
    }

    private void initWithSomeProjects(List<ProjectVO> projectVOs) {
        this.projectQuery.setProjectVOs(projectVOs);
    }

    @Test
    public void test_az_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final List<TaskVO> taskVOs = new ArrayList<>();
        taskVOs.add(new FormatTaskToTaskVO(task1, this.projectQuery).format());
        taskVOs.add(new FormatTaskToTaskVO(task2, this.projectQuery).format());
        taskVOs.add(new FormatTaskToTaskVO(task3, this.projectQuery).format());

        Collections.sort(taskVOs, new TaskVO.TaskAZComparator());
        assert(taskVOs.get(0).getId() == task1.getId());
        assert(taskVOs.get(1).getId() == task3.getId());
        assert(taskVOs.get(2).getId() == task2.getId());
    }

    @Test
    public void test_za_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final List<TaskVO> taskVOs = new ArrayList<>();
        taskVOs.add(new FormatTaskToTaskVO(task1, this.projectQuery).format());
        taskVOs.add(new FormatTaskToTaskVO(task2, this.projectQuery).format());
        taskVOs.add(new FormatTaskToTaskVO(task3, this.projectQuery).format());

        Collections.sort(taskVOs, new TaskVO.TaskZAComparator());
        assert(taskVOs.get(0).getId() == task2.getId());
        assert(taskVOs.get(1).getId() == task3.getId());
        assert(taskVOs.get(2).getId() == task1.getId());
    }

    @Test
    public void test_recent_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final List<TaskVO> taskVOs = new ArrayList<>();
        taskVOs.add(new FormatTaskToTaskVO(task1, this.projectQuery).format());
        taskVOs.add(new FormatTaskToTaskVO(task2, this.projectQuery).format());
        taskVOs.add(new FormatTaskToTaskVO(task3, this.projectQuery).format());

        Collections.sort(taskVOs, new TaskVO.TaskRecentComparator());
        assert(taskVOs.get(0).getId() == task3.getId());
        assert(taskVOs.get(1).getId() == task2.getId());
        assert(taskVOs.get(2).getId() == task1.getId());
    }

    @Test
    public void test_old_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final List<TaskVO> taskVOs = new ArrayList<>();
        taskVOs.add(new FormatTaskToTaskVO(task1, this.projectQuery).format());
        taskVOs.add(new FormatTaskToTaskVO(task2, this.projectQuery).format());
        taskVOs.add(new FormatTaskToTaskVO(task3, this.projectQuery).format());
        Collections.sort(taskVOs, new TaskVO.TaskOldComparator());

        assert(taskVOs.get(0).getId() == task1.getId());
        assert(taskVOs.get(1).getId() == task2.getId());
        assert(taskVOs.get(2).getId() == task3.getId());
    }


}