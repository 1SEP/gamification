package ru.fsep.enterprise.fseper.controllers.converters;

import ru.fsep.enterprise.fseper.controllers.dto.StepDto;
import ru.fsep.enterprise.fseper.controllers.dto.StepsDto;
import ru.fsep.enterprise.fseper.controllers.dto.TaskDto;
import ru.fsep.enterprise.fseper.controllers.dto.TasksDto;
import ru.fsep.enterprise.fseper.models.Step;
import ru.fsep.enterprise.fseper.models.Task;

import java.util.List;

/**
 * Created by ���� on 15.07.2015.
 */
public interface TasksAndStepsConverter {
    TaskDto fromTask(Task entity);
    TasksDto fromTasks(List<Task> entities);
    StepDto fromStep(Step entity);
    StepsDto fromSteps(List<Step> entities);
    Task toTask(TaskDto taskDto);
    List<Task> toTasks(TasksDto tasksDto);
    Step toStep(StepDto stepDto);
    List<Step> toSteps(StepsDto stepsDto);

}
