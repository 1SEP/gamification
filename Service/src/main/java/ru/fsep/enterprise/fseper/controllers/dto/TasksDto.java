package ru.fsep.enterprise.fseper.controllers.dto;

import java.util.List;

/**
 * Created by Ôëþð on 15.07.2015.
 */
public class TasksDto implements DataTransferObject{
    List<TaskDto> taskDtos;

    public List<TaskDto> getTaskDtos() {
        return taskDtos;
    }

    public void setTaskDtos(List<TaskDto> taskDtos) {
        this.taskDtos = taskDtos;
    }
}
