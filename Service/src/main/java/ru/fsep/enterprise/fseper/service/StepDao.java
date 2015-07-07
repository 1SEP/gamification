package ru.fsep.enterprise.fseper.service;

import ru.fsep.enterprise.fseper.models.Step;

import java.util.List;

/**
 * Created by Ôëşğ on 07.07.2015.
 */
public interface StepDao {
    void addStep(Step step);
    Step getStep(int stepId);
    void updateStep(int steId);
    List<Step> getFinishedStepList();
}
