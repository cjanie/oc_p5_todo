package com.cleanup.todoc.read.businesslogic.usecases;

import com.cleanup.todoc.read.adapters.secondary.InMemoryProjectQuery;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNull;

public class RetrieveProjectByIdTest {

    private final InMemoryProjectQuery projectQuery = new InMemoryProjectQuery();
    private final ProjectVO projectVO1 = new ProjectVO(1l, "projet1", 10);
    private final ProjectVO projectVO2 = new ProjectVO(2l, "projet2", 20);

    @Test
    public void shouldReturnNullWhenProjectDoesNotExist() {
        InMemoryProjectQuery projectQuery = new InMemoryProjectQuery();
        assertNull(new RetrieveProjectById(projectQuery, 1l).handle());
        this.initWithSomeProjects(Arrays.asList(new ProjectVO[] {this.projectVO1, this.projectVO2}));
        assertNull(new RetrieveProjectById(this.projectQuery, 3l).handle());
    }

    @Test
    public void shouldRetrieveProjectWhenExists() {
        this.initWithSomeProjects(Arrays.asList(new ProjectVO[] {this.projectVO1, this.projectVO2}));
        this.assertRetrievedProject(projectVO1);
    }

    private void initWithSomeProjects(List<ProjectVO> projectVOs) {
        this.projectQuery.setProjectVOs(projectVOs);
    }

    private void assertRetrievedProject(ProjectVO projectVO) {
        assert(new RetrieveProjectById(projectQuery, projectVO.getId()).handle().getId() == projectVO.getId());
    }
}
