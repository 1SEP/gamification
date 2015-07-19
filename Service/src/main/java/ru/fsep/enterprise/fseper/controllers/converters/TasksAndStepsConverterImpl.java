package ru.fsep.enterprise.fseper.controllers.converters;

import com.inspiresoftware.lib.dto.geda.adapter.BeanFactory;
import com.inspiresoftware.lib.dto.geda.adapter.ValueConverter;
import com.inspiresoftware.lib.dto.geda.assembler.Assembler;
import com.inspiresoftware.lib.dto.geda.assembler.DTOAssembler;
import ru.fsep.enterprise.fseper.controllers.dto.StepDto;
import ru.fsep.enterprise.fseper.controllers.dto.StepsDto;
import ru.fsep.enterprise.fseper.controllers.dto.TaskDto;
import ru.fsep.enterprise.fseper.controllers.dto.TasksDto;
import ru.fsep.enterprise.fseper.models.Step;
import ru.fsep.enterprise.fseper.models.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Ôëþð on 15.07.2015.
 */
public class TasksAndStepsConverterImpl implements TasksAndStepsConverter {

    private final String INT_TO_STR_ADAPTER_NAME = "IntegerToString";
    private final String BOOL_TO_STR_ADAPTER_NAME = "BooleanToString";
    private final String DATE_TO_STR_SDSPTER_NAME = "DateToString";

    private final ValueConverter integerToStringConverter = new ValueConverter() {
        public Object convertToDto(Object o, BeanFactory beanFactory) {
            return String.valueOf(o);
        }

        public Object convertToEntity(Object o, Object o1, BeanFactory beanFactory) {
            return Integer.parseInt(o.toString());
        }
    };

    private final ValueConverter booleanToStringConverter = new ValueConverter() {
        public Object convertToDto(Object o, BeanFactory beanFactory) {
            return String.valueOf(o);
        }

        public Object convertToEntity(Object o, Object o1, BeanFactory beanFactory) {
            return Boolean.parseBoolean(o.toString());
        }
    };

    private final ValueConverter dateToStringConverter = new ValueConverter() {
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
        adapters.put(INT_TO_STR_ADAPTER_NAME, integerToStringConverter);
        adapters.put(BOOL_TO_STR_ADAPTER_NAME, booleanToStringConverter);
        adapters.put(DATE_TO_STR_SDSPTER_NAME, dateToStringConverter);
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
        adapters.put(INT_TO_STR_ADAPTER_NAME, integerToStringConverter);
        adapters.put(BOOL_TO_STR_ADAPTER_NAME, booleanToStringConverter);
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


}
