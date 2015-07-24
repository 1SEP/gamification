package ru.fsep.enterprise.fseper.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

/**
 * Author Ôëþð on 15.07.2015
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Dto
public class StepDto {
    @DtoField(converter = "IntegerAndStringConvert")
    private String id;
    @DtoField(converter = "IntegerAndStringConvert")
    private String taskId;
    @DtoField
    private String description;
    @DtoField(converter = "BooleanAndStringConvert")
    private String finished;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }
}
