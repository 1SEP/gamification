package ru.fsep.enterprise.fseper.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
<<<<<<< HEAD
import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;
=======
>>>>>>> 9ebed1eff7e81ca669fbdbe604095f43e7b5eb1b

/**
 * Created by Ôëþð on 14.07.2015.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Dto
public class TaskDto implements DataTransferObject{
    @DtoField  (converter = "IntegerToString")
    private int id;
    @DtoField (converter = "BooleanToString")
    private String privated;
    @DtoField
    private String description;
    @DtoField (converter = "DateToString")
    private String dueDate;
    @DtoField
    private StepsDto steps;
    @DtoField (converter = "BooleanToString")
    private String finished;

    public void setId(int id) {
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

    public void setSteps(StepsDto steps) {
        this.steps = steps;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }
}
