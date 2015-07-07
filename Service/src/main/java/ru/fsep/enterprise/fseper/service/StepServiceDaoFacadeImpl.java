package ru.fsep.enterprise.fseper.service;

import ru.fsep.enterprise.fseper.models.Step;

import java.util.List;

/**
 * Created by Ôëþð on 07.07.2015.
 */
public class StepServiceDaoFacadeImpl implements StepServiceDaoFacade {
    private StepDao stepDao;

    public StepServiceDaoFacadeImpl(StepDao stepDao) {
        this.stepDao = stepDao;
    }

    public void addStep(Step step) {
        stepDao.addStep(step);
    }

    public Step getStep(int stepId) {
        return stepDao.getStep(stepId);
    }

    public void updateStep(int steId) {
        stepDao.updateStep(steId);
    }

    public List<Step> getFinishedStepList() {
        return getFinishedStepList();
    }
}
