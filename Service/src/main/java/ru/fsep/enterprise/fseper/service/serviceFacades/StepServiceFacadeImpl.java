package ru.fsep.enterprise.fseper.service.serviceFacades;

import ru.fsep.enterprise.fseper.models.Step;
import ru.fsep.enterprise.fseper.service.dao.StepDao;

import java.util.List;

/**
 * Created by Ôëþð on 07.07.2015.
 */
public class StepServiceFacadeImpl implements StepServiceFacade {
    private StepDao stepDao;

    public StepServiceFacadeImpl(StepDao stepDao) {
        this.stepDao = stepDao;
    }

    public void addStep(Step step) {
        stepDao.addStep(step);
    }

    public Step getStep(int stepId) {
        return stepDao.getStep(stepId);
    }

    public void updateStep(int stepId) {
        stepDao.updateStep(stepId);
    }

    public List<Step> getStepsByFilter(boolean finished) {
        return stepDao.getStepsByFilter(finished);
    }


    public List<Step> getStepList() {
        return stepDao.getStepList();
    }
}
