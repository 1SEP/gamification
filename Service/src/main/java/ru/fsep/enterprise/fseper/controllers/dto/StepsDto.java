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
public class StepsDto {
    @DtoField
    List<StepDto> stepDtos;

    public List<StepDto> getStepDtos() {
        return stepDtos;
    }

    public void setStepDtos(List<StepDto> stepDtos) {
        this.stepDtos = stepDtos;
    }
}
