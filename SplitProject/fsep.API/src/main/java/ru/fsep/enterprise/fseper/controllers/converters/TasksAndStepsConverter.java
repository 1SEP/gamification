package ru.fsep.enterprise.fseper.controllers.converters;


import ru.fsep.enterprise.fseper.controllers.dto.StepDto;
import ru.fsep.enterprise.fseper.controllers.dto.TaskDto;
import ru.fsep.enterprise.fseper.models.Step;
import ru.fsep.enterprise.fseper.models.Steps;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.models.Tasks;

import java.util.List;

/**
 * Created by Ôëþð on 15.07.2015.
 */
public interface TasksAndStepsConverter {
    TaskDto fromTask(Task entity);
    List<TaskDto> fromTasks(Tasks entities);
    StepDto fromStep(Step entity);
    List<StepDto> fromSteps(Steps entities);
    Task toTask(TaskDto taskDto);
    List<Task> toTasks(List<TaskDto> tasksDto);
    Step toStep(StepDto stepDto);
    List<Step> toSteps(List<StepDto> stepsDto);

}
