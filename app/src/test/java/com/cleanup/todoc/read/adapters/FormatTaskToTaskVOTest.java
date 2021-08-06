package com.cleanup.todoc.read.adapters;

import com.cleanup.todoc.modelpersistance.Project;
import com.cleanup.todoc.modelpersistance.Task;
import com.cleanup.todoc.read.adapters.secondary.FormatProjectToProjectVO;
import com.cleanup.todoc.read.adapters.secondary.FormatTaskToTaskVO;
import com.cleanup.todoc.read.adapters.secondary.InMemoryProjectQuery;
import com.cleanup.todoc.read.adapters.secondary.InMemoryTaskQuery;
import com.cleanup.todoc.read.businesslogic.usecases.ProjectVO;
import com.cleanup.todoc.read.businesslogic.usecases.TaskVO;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNull;

public class FormatTaskToTaskVOTest {



    @Before
    public void setUp() {

    }

    @Test
    public void taskShoudBeFormatted() {

        Task task = new Task(1l, 1l, "task 1", 10l);

        InMemoryProjectQuery projectQuery = new InMemoryProjectQuery();
        Project project = new Project(1l, "project 1", 100);
        projectQuery.setProjectVOs(Arrays.asList(new ProjectVO[] {new FormatProjectToProjectVO(project).format()}));

        assert(new FormatTaskToTaskVO(task, projectQuery).format().getProjectVO().getId() == 1l);
        assert(new FormatTaskToTaskVO(task, projectQuery).format().getId() == 1l);
    }

}
