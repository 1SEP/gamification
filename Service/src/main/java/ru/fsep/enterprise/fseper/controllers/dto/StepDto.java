package ru.fsep.enterprise.fseper.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
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

    @Override
    public int hashCode() {
        return Objects.hashCode(id, taskId, description, finished);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final StepDto other = (StepDto) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.taskId, other.taskId)
                && Objects.equal(this.description, other.description)
                && Objects.equal(this.finished, other.finished);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("taskId", taskId)
                .add("description", description)
                .add("finished", finished)
                .toString();
    }
}
