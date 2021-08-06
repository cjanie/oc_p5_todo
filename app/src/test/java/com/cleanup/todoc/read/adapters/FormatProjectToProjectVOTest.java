package com.cleanup.todoc.read.adapters;

import com.cleanup.todoc.modelpersistance.Project;
import com.cleanup.todoc.read.adapters.secondary.FormatProjectToProjectVO;
import com.cleanup.todoc.read.businesslogic.usecases.ProjectVO;

import org.junit.Test;

public class FormatProjectToProjectVOTest {

    private final Project project1 = new Project(1l, "projet1", 10);
    private final Project project2 = new Project(2l, "projet2", 20);

    private final ProjectVO projectVO1 = new ProjectVO(1l, "projet1", 10);
    private final ProjectVO projectVO2 = new ProjectVO(2l, "projet2", 20);

    @Test
    public void projectShouldBeFormated() {
        assert(new FormatProjectToProjectVO(project1).format().getName().equals(projectVO1.getName()));
        assert(new FormatProjectToProjectVO(project2).format().getId() == projectVO2.getId());
    }

}
