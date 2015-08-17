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
public class StepsDto {
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("stepDtos", stepDtos)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(stepDtos);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final StepsDto other = (StepsDto) obj;
        return Objects.equal(this.stepDtos, other.stepDtos);
    }

    @DtoField

    List<StepDto> stepDtos;

    public List<StepDto> getStepDtos() {
        return stepDtos;
    }

    public void setStepDtos(List<StepDto> stepDtos) {
        this.stepDtos = stepDtos;
    }
}
