package ru.fsep.enterprise.fseper;

import ru.fsep.enterprise.fseper.controllers.TasksController;
import ru.fsep.enterprise.fseper.controllers.converters.TasksAndStepsConverter;
import ru.fsep.enterprise.fseper.controllers.converters.TasksAndStepsConverterImpl;
import ru.fsep.enterprise.fseper.controllers.dto.StepDto;
import ru.fsep.enterprise.fseper.controllers.dto.TaskDto;
import ru.fsep.enterprise.fseper.models.Step;
import ru.fsep.enterprise.fseper.models.Task;

import java.util.List;

/**
 * Created by Ôëþð on 21.07.2015.
 */
public class main {
    public static void main(String[] args) {
        Task task = new Task();
        TaskDto taskDto = new TaskDto();
        TasksAndStepsConverter converter = new TasksAndStepsConverterImpl();
        taskDto = converter.fromTask(Data.USER.getTasks().get(0));

        System.out.println(taskDto.getId());
        System.out.println(taskDto.getDescription());
        System.out.println(taskDto.getDueDate());
        System.out.println(taskDto.getFinished());
        System.out.println(taskDto.getPrivated());
        System.out.println(taskDto.getFinished().getClass());
        System.out.println(taskDto.getDueDate().getClass());
        List<StepDto> steps = taskDto.getSteps();
        System.out.println("steps");
        for (StepDto step : steps) {
            System.out.println(step.getDescription());
        }

    }
}
