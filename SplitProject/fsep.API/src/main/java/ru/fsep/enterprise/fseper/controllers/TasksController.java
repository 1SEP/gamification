package ru.fsep.enterprise.fseper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fsep.enterprise.fseper.controllers.converters.TasksAndStepsConverter;
import ru.fsep.enterprise.fseper.controllers.dto.ResponseDto;
import ru.fsep.enterprise.fseper.controllers.dto.StepDto;
import ru.fsep.enterprise.fseper.controllers.dto.TaskDto;
import ru.fsep.enterprise.fseper.models.Step;
import ru.fsep.enterprise.fseper.models.Steps;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.service.facades.TasksServiceFacade;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ���� on 13.07.2015.
 */
@RestController
@RequestMapping(value = "/tasks/")
public class TasksController {

    @Autowired
    private TasksServiceFacade tasksServiceFacade;

    @Autowired
    private TasksAndStepsConverter tasksAndStepsConverter;

    @RequestMapping(value = "{task-id}.json", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto> getTask(@PathVariable("task-id") int taskId) {
        Task task = tasksServiceFacade.getTask(taskId);
        TaskDto taskDto = tasksAndStepsConverter.fromTask(task);
        return ResponseBuilder.buildResponseGet(taskDto);
    }

    @RequestMapping(value = "{task-id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseDto> updateTask(@PathVariable("task-id") int taskId, @RequestBody TaskDto taskDto) {
        Task task = tasksAndStepsConverter.toTask(taskDto);
        tasksServiceFacade.updateTask(task);
        return ResponseBuilder.buildResponsePut(taskDto);
    }

    @RequestMapping(value = "{task-id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDto> removeTask(@PathVariable("task-id") int taskId) {
        tasksServiceFacade.removeTask(taskId);
        return ResponseBuilder.buildResponseDelete();
    }

    @RequestMapping(value = "{task-id}/steps.json", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto> getSteps(@PathVariable("task-id") int id) {
        Steps steps = tasksServiceFacade.getTask(id).getSteps();
        List<StepDto> stepsDto = tasksAndStepsConverter.fromSteps(steps);
        return ResponseBuilder.buildResponseGet(stepsDto);
    }

    @RequestMapping(value = "{task-id}/steps/{step-id}.json", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto> getStep(@PathVariable("task-id") int taskId,
                                               @PathVariable("step-id") int stepId) {
        Steps steps = tasksServiceFacade.getTask(taskId).getSteps();
        Step step = null;
        for (Step s : steps.getSteps()) {
            if (stepId == s.getId() && taskId == s.getTaskId()) step = s;
        }
        StepDto stepDto = tasksAndStepsConverter.fromStep(step);
        return ResponseBuilder.buildResponseGet(stepDto);
    }

    @RequestMapping(value = "{task-id}/steps.json/filter={finished}", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto> getStepsByFilterFinished(@PathVariable("task-id") int taskId,
                                                                @PathVariable("finished") boolean finished) {
        Steps steps = tasksServiceFacade.getTask(taskId).getSteps();
        List<Step> result = new LinkedList<Step>();
        for (Step s : steps.getSteps()) {
            if (finished == s.isFinished() && taskId == s.getTaskId()) result.add(s);
        }
        List<StepDto> stepsDto = tasksAndStepsConverter.fromSteps(new Steps(result));
        return ResponseBuilder.buildResponseGet(stepsDto);
    }

    @RequestMapping(value = "{task-id}/steps/assignments", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> addStep(@PathVariable("task-id") int taskId, @RequestBody StepDto stepDto) {
        Task task = tasksServiceFacade.getTask(taskId);
        Steps steps = task.getSteps();
        Step step = tasksAndStepsConverter.toStep(stepDto);
        steps.getSteps().add(step);
        return ResponseBuilder.buildResponsePut(stepDto);
    }

    @RequestMapping(value = "{task-id}/steps", method = RequestMethod.PUT)
    public ResponseEntity<ResponseDto> updateStep(@PathVariable("task-id") int taskId, @RequestBody StepDto stepDto) {
        Step step = tasksAndStepsConverter.toStep(stepDto);
        tasksServiceFacade.updateStep(taskId, step);
        return ResponseBuilder.buildResponsePut(stepDto);
    }

    @RequestMapping(value = "{task-id}/steps/{step-id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDto> removeStep(@PathVariable("task-id") int taskId, @PathVariable("step-id") int stepId) {
        tasksServiceFacade.removeStep(taskId, stepId);
        return ResponseBuilder.buildResponseDelete();
    }
}
