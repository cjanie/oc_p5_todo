package com.cleanup.todoc.businesslogic.usecases;

import com.cleanup.todoc.adapters.secondary.InMemoryProjectQuery;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class RetrieveProjectsTest {

    private final InMemoryProjectQuery projectQuery = new InMemoryProjectQuery();
    private final ProjectVO projectVO1 = new ProjectVO(1l);
    private final ProjectVO projectVO2 = new ProjectVO(2l);

    @Test
    public void shouldBeEmptyWhenThereIsNone() {
        InMemoryProjectQuery projectQuery = new InMemoryProjectQuery();
        assert(Arrays.asList(new RetrieveProjects(projectQuery).handle()).isEmpty());
    }

    @Test
    public void shouldRetrieveProjectsWhenThereAreSome() {
        this.initWithSomeProjects(Arrays.asList(projectVO1, projectVO2));
        this.assertRetrievedProjects(Arrays.asList(projectVO1, projectVO2));
    }

    private void initWithSomeProjects(List<ProjectVO> projectVOs) {
        this.projectQuery.setProjectVOs(projectVOs);
    }

    private void assertRetrievedProjects(List<ProjectVO> projectVOs) {
        assert(Arrays.asList(new RetrieveProjects(this.projectQuery).handle()).equals(projectVOs));
    }
}
