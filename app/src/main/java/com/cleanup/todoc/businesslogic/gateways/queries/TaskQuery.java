package com.cleanup.todoc.businesslogic.gateways.queries;

import com.cleanup.todoc.businesslogic.usecases.TaskVO;

import java.util.List;

public interface TaskQuery {

    List<TaskVO> retrieveAll();
}
