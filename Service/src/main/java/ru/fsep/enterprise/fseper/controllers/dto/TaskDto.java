package ru.fsep.enterprise.fseper.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inspiresoftware.lib.dto.geda.annotations.Dto;
import com.inspiresoftware.lib.dto.geda.annotations.DtoField;

/**
 * Created by ���� on 14.07.2015.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Dto
public class TaskDto {
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
}
