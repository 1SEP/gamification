package ru.fsep.enterprise.fseper.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;
import ru.fsep.enterprise.fseper.models.Step;

import java.util.List;

/**
 * Author Ôëþð on 14.07.2015
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Dto
public class TaskDto {
    @DtoField  (converter = "IntegerAndStringConvert")
    private String id;
    @DtoField (converter = "BooleanAndStringConvert")
    private String privated;
    @DtoField
    private String description;
    @DtoField (converter = "DateAndStringConvert")
    private String dueDate;
//    @DtoField
//    private StepsDto steps;
 //   @DtoField(dtoBeanKey = "StepsDtoBeanKey")
    private List<StepDto> steps;
    @DtoField (converter = "BooleanAndStringConvert")
    private String finished;

    public void setId(String id) {
        this.id = id;
    }

    public void setPrivated(String privated) {
        this.privated = privated;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

//    public void setSteps(StepsDto steps) {
//        this.steps = steps;
//    }


    public List<StepDto> getSteps() {
        return steps;
    }

    public void setSteps(List<StepDto> steps) {
        this.steps = steps;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public String getId() {
        return id;
    }

    public String getPrivated() {
        return privated;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

//    public StepsDto getSteps() {
//        return steps;
//    }

    public String getFinished() {
        return finished;
    }
}
