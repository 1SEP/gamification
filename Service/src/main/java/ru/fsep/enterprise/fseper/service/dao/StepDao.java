package ru.fsep.enterprise.fseper.service.dao;

import ru.fsep.enterprise.fseper.models.Step;

import java.util.List;

/**
 * Created by ���� on 07.07.2015.
 */
public interface StepDao {
    void addStep(Step step);
    Step getStep(int stepId);
    void updateStep(int stepId);
    List<Step> getStepsByFilter(boolean finished);
    List<Step> getStepList();
}
