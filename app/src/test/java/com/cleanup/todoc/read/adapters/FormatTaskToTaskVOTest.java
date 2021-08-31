package com.cleanup.todoc.read.adapters;

import com.cleanup.todoc.modelpersistance.Project;
import com.cleanup.todoc.modelpersistance.Task;
import com.cleanup.todoc.read.adapters.secondary.FormatTaskToTaskVO;
import com.cleanup.todoc.read.InMemoryProjectQuery;

import org.junit.Test;

public class FormatTaskToTaskVOTest {

    @Test
    public void taskShoudBeFormatted() {
        Task task = new Task(1l, 1l, "task 1", 10l);

        InMemoryProjectQuery projectQuery = new InMemoryProjectQuery();
        Project project = new Project(1l, "project 1", 100);

        assert(new FormatTaskToTaskVO(task, project).format().getProjectVO().getId() == 1l);
        assert(new FormatTaskToTaskVO(task, project).format().getId() == 1l);
    }

}
