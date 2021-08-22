package com.cjanie.todoc.read.adapters.secondary;

import com.cjanie.todoc.modelpersistance.Project;
import com.cjanie.todoc.read.businesslogic.usecases.ProjectVO;

public class FormatProjectToProjectVO {

    private Project project;

    public FormatProjectToProjectVO(Project project) {
        this.project = project;
    }

    public ProjectVO format() {
        ProjectVO projectVO = null;
        if(this.project != null) {
            projectVO = new ProjectVO(project.getId(), project.getName(), project.getColor());
        }
        return projectVO;
    }
}
