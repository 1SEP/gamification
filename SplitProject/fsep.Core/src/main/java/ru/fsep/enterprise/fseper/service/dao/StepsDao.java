package ru.fsep.enterprise.fseper.service.dao;

import ru.fsep.enterprise.fseper.models.Step;
import ru.fsep.enterprise.fseper.models.Steps;

import java.util.List;

public interface StepsDao {
    Steps getSteps(int taskId);
    Step getStep(int taskId, int stepId);
    Steps getStepsByFinishedFilter(int taskId, boolean finished);
    void addStep(int taskId, Step step);
    Step updateStep(int taskId, Step step);
    void removeStep(int taskId, int stepId);
}
