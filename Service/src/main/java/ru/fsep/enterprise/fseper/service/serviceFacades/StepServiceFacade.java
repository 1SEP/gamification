package ru.fsep.enterprise.fseper.service.serviceFacades;

import ru.fsep.enterprise.fseper.models.Step;

import java.util.List;

/**
 * Created by Ôëþð on 07.07.2015.
 */
public interface StepServiceFacade {
    void addStep(Step step);
    Step getStep(int stepId);
    void updateStep(int stepId);
    List<Step> getStepsByFilter(boolean finished);
    List<Step> getStepList();
}
