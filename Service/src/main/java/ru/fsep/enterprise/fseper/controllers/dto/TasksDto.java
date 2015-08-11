package ru.fsep.enterprise.fseper.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
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

    @Override
    public int hashCode() {
        return Objects.hashCode(taskDtos);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("taskDtos", taskDtos)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final TasksDto other = (TasksDto) obj;
        return Objects.equal(this.taskDtos, other.taskDtos);
    }
}
