package ru.fsep.enterprise.fseper.controllers.converters;

import com.inspiresoftware.lib.dto.geda.adapter.BeanFactory;
import com.inspiresoftware.lib.dto.geda.adapter.ValueConverter;
import com.inspiresoftware.lib.dto.geda.assembler.Assembler;
import com.inspiresoftware.lib.dto.geda.assembler.DTOAssembler;
import org.springframework.stereotype.Component;
import ru.fsep.enterprise.fseper.controllers.dto.StepDto;
import ru.fsep.enterprise.fseper.controllers.dto.StepsDto;
import ru.fsep.enterprise.fseper.controllers.dto.TaskDto;
import ru.fsep.enterprise.fseper.controllers.dto.TasksDto;
import ru.fsep.enterprise.fseper.models.Step;
import ru.fsep.enterprise.fseper.models.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Ôëþð on 15.07.2015.
 */

public class TasksAndStepsConverterImpl implements TasksAndStepsConverter {

    private final String INT_STR_ADAPTER_NAME = "IntegerAndStringConvert";
    private final String BOOL_STR_ADAPTER_NAME = "BooleanAndStringConvert";
    private final String DATE_STR_ADAPTER_NAME = "DateAndStringConvert";

    private final ValueConverter integerAndStringConverter = new ValueConverter() {
        public Object convertToDto(Object o, BeanFactory beanFactory) {
            return String.valueOf(o);
        }

        public Object convertToEntity(Object o, Object o1, BeanFactory beanFactory) {
            return Integer.parseInt(o.toString());
        }
    };

    private final ValueConverter booleanAndStringConverter = new ValueConverter() {
        public Object convertToDto(Object o, BeanFactory beanFactory) {
            return String.valueOf(o);
        }

        public Object convertToEntity(Object o, Object o1, BeanFactory beanFactory) {
            return Boolean.parseBoolean(o.toString());
        }
    };

    private final ValueConverter dateAndStringConverter = new ValueConverter() {
        public Object convertToDto(Object o, BeanFactory beanFactory) {
            return String.valueOf(o);
        }

        public Object convertToEntity(Object o, Object o1, BeanFactory beanFactory) {
            SimpleDateFormat formatter= new SimpleDateFormat("MMM dd, yyyy");
            try {
                return formatter.parse(o.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    private final Assembler taskAssembler = DTOAssembler.newAssembler(TaskDto.class, TasksDto.class);
    private final Assembler stepAssembler = DTOAssembler.newAssembler(Step.class, StepsDto.class);
    public TaskDto fromTask(Task entity) {
        TaskDto taskDto = new TaskDto();
        Map<String, Object> adapters = new HashMap<String, Object>();
        adapters.put(INT_STR_ADAPTER_NAME, integerAndStringConverter);
        adapters.put(BOOL_STR_ADAPTER_NAME, booleanAndStringConverter);
        adapters.put(DATE_STR_ADAPTER_NAME, dateAndStringConverter);
        //DtoBeanFactory bean = new DtoBeanFactory();
        taskDto.setSteps(fromSteps(entity.getSteps()));
        taskAssembler.assembleDto(taskDto, entity, adapters, null);
        return taskDto;
    }

    public TasksDto fromTasks(List<Task> entities) {
        List<TaskDto> tasksDtos = new LinkedList<TaskDto>();
        for (Task task : entities) {
            tasksDtos.add(fromTask(task));
        }
        TasksDto tasksDto = new TasksDto();
        tasksDto.setTaskDtos(tasksDtos);
        return tasksDto;
    }

    public StepDto fromStep(Step entity) {
        StepDto stepDto  = new StepDto();
        Map<String, Object> adapters = new HashMap<String, Object>();
        adapters.put(INT_STR_ADAPTER_NAME, integerAndStringConverter);
        adapters.put(BOOL_STR_ADAPTER_NAME, booleanAndStringConverter);
        stepAssembler.assembleDto(stepDto, entity, adapters, null);
        return stepDto;
    }

    public StepsDto fromSteps(List<Step> entities) {
        List<StepDto> stepDtos = new LinkedList<StepDto>();
        for (Step step : entities) {
            stepDtos.add(fromStep(step));
        }
        StepsDto stepsDto = new StepsDto();
        stepsDto.setStepDtos(stepDtos);
        return stepsDto;
    }

    public Task toTask(TaskDto taskDto) {
        Task task = new Task();
        Map<String, Object> adapters = new HashMap<String, Object>();
        adapters.put(INT_STR_ADAPTER_NAME, integerAndStringConverter);
        adapters.put(BOOL_STR_ADAPTER_NAME, booleanAndStringConverter);
        adapters.put(DATE_STR_ADAPTER_NAME, dateAndStringConverter);
        task.setSteps(toSteps(taskDto.getSteps()));
        taskAssembler.assembleEntity(taskDto, task, adapters, null);
        return task;
    }

    public List<Task> toTasks(TasksDto tasksDto) {
        List<Task> tasks = new LinkedList<Task>();
        TaskDto taskDto;
        for(int i=0; i< tasks.size(); i++){
            taskDto = tasksDto.getTaskDtos().get(i);
            tasks.add(toTask(taskDto));
        }
        return tasks;
    }

    public Step toStep(StepDto stepDto) {
        Step step = new Step();
        Map<String, Object> adapters = new HashMap<String, Object>();
        adapters.put(INT_STR_ADAPTER_NAME, integerAndStringConverter);
        adapters.put(BOOL_STR_ADAPTER_NAME, booleanAndStringConverter);
        stepAssembler.assembleEntity(stepDto, step, adapters, null);
        return step;
    }

    public List<Step> toSteps(StepsDto stepsDto) {
        List<Step> steps = new LinkedList<Step>();
        StepDto stepDto;
        for(int i=0; i < steps.size(); i++){
            stepDto = stepsDto.getStepDtos().get(i);
            steps.add(toStep(stepDto));
        }
        return steps;
    }
}
