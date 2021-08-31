package com.cleanup.todoc.read.businesslogic.usecases;

import com.cleanup.todoc.modelpersistance.Project;
import com.cleanup.todoc.modelpersistance.Task;
import com.cleanup.todoc.read.adapters.secondary.FormatTaskToTaskVO;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

// @author Gaëtan HERFRAY
public class TaskVOUnitTest {


    private final Project project1 = new Project(1l, "projet1", 10);
    private final Project project2 = new Project(2l, "projet2", 20);
    private final Project project3 = new Project(3l, "projet3", 30);


    @Test
    public void test_az_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final List<TaskVO> taskVOs = new ArrayList<>();
        taskVOs.add(new FormatTaskToTaskVO(task1, this.project1).format());
        taskVOs.add(new FormatTaskToTaskVO(task2, this.project2).format());
        taskVOs.add(new FormatTaskToTaskVO(task3, this.project3).format());

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
        taskVOs.add(new FormatTaskToTaskVO(task1, this.project1).format());
        taskVOs.add(new FormatTaskToTaskVO(task2, this.project2).format());
        taskVOs.add(new FormatTaskToTaskVO(task3, this.project3).format());

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
        taskVOs.add(new FormatTaskToTaskVO(task1, this.project1).format());
        taskVOs.add(new FormatTaskToTaskVO(task2, this.project2).format());
        taskVOs.add(new FormatTaskToTaskVO(task3, this.project3).format());

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
        taskVOs.add(new FormatTaskToTaskVO(task1, this.project1).format());
        taskVOs.add(new FormatTaskToTaskVO(task2, this.project2).format());
        taskVOs.add(new FormatTaskToTaskVO(task3, this.project3).format());
        Collections.sort(taskVOs, new TaskVO.TaskOldComparator());

        assert(taskVOs.get(0).getId() == task1.getId());
        assert(taskVOs.get(1).getId() == task2.getId());
        assert(taskVOs.get(2).getId() == task3.getId());
    }

}