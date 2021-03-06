package com.cleanup.todoc.read.businesslogic.gateways.queries;

import com.cleanup.todoc.read.businesslogic.usecases.TaskVO;

import java.util.List;

public interface TaskQuery {

    List<TaskVO> retrieveAll();

    List<TaskVO> retrieveTasksByProject(long projectId);
}
