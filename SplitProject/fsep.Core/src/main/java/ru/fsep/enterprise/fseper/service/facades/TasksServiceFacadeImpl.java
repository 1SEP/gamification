package ru.fsep.enterprise.fseper.service.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fsep.enterprise.fseper.models.Step;
import ru.fsep.enterprise.fseper.models.Steps;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.models.Tasks;
import ru.fsep.enterprise.fseper.service.dao.StepsDao;
import ru.fsep.enterprise.fseper.service.dao.TasksDao;

import java.util.Date;

/**
 * Created by Ôëþð on 28.08.2015.
 */
@Service
public class TasksServiceFacadeImpl implements TasksServiceFacade {
    @Autowired
    private TasksDao tasksDao;
    @Autowired
    private StepsDao stepsDao;

    public void assignmentTask(Task task, int userId) {
        tasksDao.assignmentTask(task, userId);
    }


    public Task getTask(int taskId) {
        return tasksDao.getTask(taskId);
    }

    public Tasks getTasks(int userId) {
        return tasksDao.getTasks(userId);
    }

    public Task updateTask(Task task) {
        return tasksDao.updateTask(task);
    }

    public void removeTask(int taskId) {
        tasksDao.removeTask(taskId);
    }

    public Tasks getTasksByPrivatedFilter(int userId, boolean privated) {
        return tasksDao.getTasksByPrivatedFilter(userId, privated);
    }

    public Tasks getTasksByFinishedFilter(int userId, boolean finished) {
        return tasksDao.getTasksByFinishedFilter(userId, finished);
    }

    public Tasks getTasksByDate(int usersId, Date date) {
        return tasksDao.getTasksByDate(usersId, date);
    }

    public Steps getSteps(int taskId) {
        return stepsDao.getSteps(taskId);
    }

    public Step getStep(int taskId, int stepId) {
        return stepsDao.getStep(taskId, stepId);
    }

    public Steps getStepsByFinishedFilter(int taskId, boolean finished) {
        return stepsDao.getStepsByFinishedFilter(taskId, finished);
    }

    public void addStep(int taskId, Step step) {
        stepsDao.addStep(taskId, step);
    }

    public Step updateStep(int taskId, Step step) {
        return stepsDao.updateStep(taskId, step);
    }

    public void removeStep(int taskId, int stepId) {
        stepsDao.removeStep(taskId, stepId);
    }
}
