package ru.fsep.enterprise.fseper.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

import java.util.List;

/**
 * Author Ôëþð on 14.07.2015
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Dto
public class TaskDto {
    @DtoField(converter = "IntegerAndStringConvert")
    private String id;

    @DtoField(converter = "BooleanAndStringConvert")
    private String privated;

    @DtoField
    private String description;

    @DtoField(converter = "DateAndStringConvert")
    private String dueDate;

    private List<StepDto> steps;

    @DtoField(converter = "BooleanAndStringConvert")
    private String finished;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrivated() {
        return privated;
    }

    public void setPrivated(String privated) {
        this.privated = privated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public List<StepDto> getSteps() {
        return steps;
    }

    public void setSteps(List<StepDto> steps) {
        this.steps = steps;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, privated, description, dueDate, steps, finished);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final TaskDto other = (TaskDto) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.privated, other.privated)
                && Objects.equal(this.description, other.description)
                && Objects.equal(this.dueDate, other.dueDate)
                && Objects.equal(this.steps, other.steps)
                && Objects.equal(this.finished, other.finished);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("privated", privated)
                .add("description", description)
                .add("dueDate", dueDate)
                .add("steps", steps)
                .add("finished", finished)
                .toString();
    }
}
