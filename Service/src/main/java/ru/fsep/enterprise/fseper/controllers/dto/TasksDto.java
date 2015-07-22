package ru.fsep.enterprise.fseper.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

import java.util.List;

/**
 * Created by Ôëþð on 15.07.2015.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Dto
public class TasksDto {
    @DtoField
    List<TaskDto> taskDtos;

    public List<TaskDto> getTaskDtos() {
        return taskDtos;
    }

    public void setTaskDtos(List<TaskDto> taskDtos) {
        this.taskDtos = taskDtos;
    }
}
