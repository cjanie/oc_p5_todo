package com.cleanup.todoc.write.adapters.secondary;

import com.cleanup.todoc.read.businesslogic.usecases.TaskVO;
import com.cleanup.todoc.write.businesslogic.gateways.commands.TaskCommand;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTaskCommand implements TaskCommand {

    List<TaskVO> tasks;

    public InMemoryTaskCommand() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public void add(TaskVO task) {
        this.tasks.add(task);
    }

    @Override
    public void deleteById(long id) {
        if(!this.tasks.isEmpty()) {
            for(int i=0; i<tasks.size(); i++) {
                if(tasks.get(i).getId() == id) {
                    tasks.remove(tasks.get(i));
                    break;
                }
            }
        }
    }

    public List<TaskVO> getTasks() {
        return tasks;
    }

}
