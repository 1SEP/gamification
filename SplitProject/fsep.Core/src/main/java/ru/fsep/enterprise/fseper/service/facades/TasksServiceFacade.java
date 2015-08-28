package ru.fsep.enterprise.fseper.service.facades;

import ru.fsep.enterprise.fseper.models.Step;
import ru.fsep.enterprise.fseper.models.Steps;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.models.Tasks;

import java.util.Date;

/**
 * Created by Ôëþð on 28.08.2015.
 */
public interface TasksServiceFacade {
    void assignmentTask(Task task, int taskId);
    Task getTask(int taskId);
    Task updateTask(Task task);
    void removeTask(int taskId);
    Tasks getTasks(int userId);
    Tasks getTasksByDate(int usersId, Date date);
    Tasks getTasksByPrivatedFilter(int userId, boolean privated);
    Tasks getTasksByFinishedFilter(int userId, boolean finished);

    Steps getSteps(int taskId);
    Step getStep(int taskId, int stepId);
    Steps getStepsByFinishedFilter(int taskId, boolean finished);
    void addStep(int taskId, Step step);
    Step updateStep(int taskId, Step step);
    void removeStep(int taskId, int stepId);
}
