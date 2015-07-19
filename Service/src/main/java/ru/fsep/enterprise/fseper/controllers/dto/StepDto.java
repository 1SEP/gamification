package ru.fsep.enterprise.fseper.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

/**
 * Author Ôëþð on 15.07.2015
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Dto
public class StepDto implements DataTransferObject{
    @DtoField (converter = "IntegerToString")
    private int id;
    @DtoField (converter = "IntegerToString")
    private String taskId;
    @DtoField
    private String description;
    @DtoField (converter = "BooleanToString")
    private String finished;
}
