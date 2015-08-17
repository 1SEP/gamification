package ru.fsep.enterprise.fseper.controllers.converters;

import com.inspiresoftware.lib.dto.geda.adapter.BeanFactory;
import com.inspiresoftware.lib.dto.geda.adapter.ValueConverter;
import com.inspiresoftware.lib.dto.geda.assembler.Assembler;
import com.inspiresoftware.lib.dto.geda.assembler.DTOAssembler;
import org.springframework.stereotype.Component;
import ru.fsep.enterprise.fseper.controllers.dto.StepDto;
import ru.fsep.enterprise.fseper.controllers.dto.TaskDto;
import ru.fsep.enterprise.fseper.models.Step;
import ru.fsep.enterprise.fseper.models.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Ôëþð on 15.07.2015.
 */
@Component
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
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("dd.mm.yyyy hh:mm");
            Date date = null;
            try {
                date = format.parse(String.valueOf(o));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }
    };

    private final Assembler taskAssembler = DTOAssembler.newAssembler(TaskDto.class, Task.class);
    private final Assembler stepAssembler = DTOAssembler.newAssembler(StepDto.class, Step.class);

    public TaskDto fromTask(Task entity) {
        TaskDto taskDto = new TaskDto();
        Map<String, Object> adapters = new HashMap<String, Object>();
        taskDto.setSteps(fromSteps(entity.getSteps()));
        adapters.put(INT_STR_ADAPTER_NAME, integerAndStringConverter);
        adapters.put(BOOL_STR_ADAPTER_NAME, booleanAndStringConverter);
        adapters.put(DATE_STR_ADAPTER_NAME, dateAndStringConverter);
        taskAssembler.assembleDto(taskDto, entity, adapters, null);
        return taskDto;
    }

    public List<TaskDto> fromTasks(List<Task> entities) {
        List<TaskDto> tasksDtos = new LinkedList<TaskDto>();
        for (Task task : entities) {
            tasksDtos.add(fromTask(task));
        }
        return tasksDtos;
    }

    public StepDto fromStep(Step entity) {
        StepDto stepDto = new StepDto();
        Map<String, Object> adapters = new HashMap<String, Object>();
        adapters.put(INT_STR_ADAPTER_NAME, integerAndStringConverter);
        adapters.put(BOOL_STR_ADAPTER_NAME, booleanAndStringConverter);
        stepAssembler.assembleDto(stepDto, entity, adapters, null);
        return stepDto;
    }

    public List<StepDto> fromSteps(List<Step> entities) {
        List<StepDto> stepDtos = new LinkedList<StepDto>();
        for (Step step : entities) {
            stepDtos.add(fromStep(step));
        }
        return stepDtos;
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

    public List<Task> toTasks(List<TaskDto> tasksDto) {
        List<Task> tasks = new LinkedList<Task>();
        for (int i = 0; i < tasks.size(); i++) {
            tasks.add(toTask(tasksDto.get(i)));
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

    public List<Step> toSteps(List<StepDto> stepsDto) {
        List<Step> steps = new LinkedList<Step>();
        for (int i = 0; i < steps.size(); i++) {
            steps.add(toStep(stepsDto.get(i)));
        }
        return steps;
    }
}
