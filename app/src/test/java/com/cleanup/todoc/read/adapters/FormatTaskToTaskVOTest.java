package com.cleanup.todoc.read.adapters;

import com.cleanup.todoc.modelpersistance.Project;
import com.cleanup.todoc.modelpersistance.Task;
import com.cleanup.todoc.read.adapters.secondary.FormatProjectToProjectVO;
import com.cleanup.todoc.read.adapters.secondary.FormatTaskToTaskVO;
import com.cleanup.todoc.read.adapters.secondary.InMemoryProjectQuery;
import com.cleanup.todoc.read.businesslogic.usecases.ProjectVO;

import org.junit.Test;

import java.util.Arrays;

public class FormatTaskToTaskVOTest {

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
