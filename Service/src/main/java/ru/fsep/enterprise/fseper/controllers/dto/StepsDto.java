package ru.fsep.enterprise.fseper.controllers.dto;

import java.util.List;

/**
 * Created by Ôëþð on 15.07.2015.
 */
public class StepsDto implements DataTransferObject{
    List<StepDto> stepDtos;

    public List<StepDto> getStepDtos() {
        return stepDtos;
    }

    public void setStepDtos(List<StepDto> stepDtos) {
        this.stepDtos = stepDtos;
    }
}
