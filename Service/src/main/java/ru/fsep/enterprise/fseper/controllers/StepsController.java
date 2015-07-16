package ru.fsep.enterprise.fseper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fsep.enterprise.fseper.controllers.converters.ConverterOfTasksAndStepsEntities;
import ru.fsep.enterprise.fseper.controllers.dto.StepDto;
import ru.fsep.enterprise.fseper.controllers.dto.StepsDto;
import ru.fsep.enterprise.fseper.models.Step;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ���� on 13.07.2015.
 */
@RestController
public class StepsController {
    @Autowired
    UsersServiceFacade usersServiceFacade;
    @Autowired
    private ConverterOfTasksAndStepsEntities converterOfTasksAndStepsEntities;

    @RequestMapping(value = "/tasks/{task-id}/steps", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getSteps(@PathVariable("task-id") int id)
    {
        List<Step> steps = usersServiceFacade.getTask(id).getSteps();
        StepsDto stepsDto = converterOfTasksAndStepsEntities.fromSteps(steps);
        return ResponseBuilder.buildResponseGet(stepsDto);
    }

    @RequestMapping(value = "task/{task-id}/steps/{step-id}.json", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getStep(@PathVariable("task-id") int taskId,
                                                     @PathVariable ("step-id") int stepId)
    {
        List<Step> steps = usersServiceFacade.getTask(taskId).getSteps();
        Step step = null;
        for (Step s : steps) {
            if (stepId == s.getId() && taskId == s.getTask_id()) step = s;
        }
        StepDto stepDto = converterOfTasksAndStepsEntities.fromStep(step);
        return ResponseBuilder.buildResponseGet(stepDto);
    }

    @RequestMapping(value = "task/{task-id}/steps.json/filter={finished}", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getStepsByFilter(@PathVariable("task-id") int taskId,
                                                              @PathVariable("finished") boolean finished)
    {
        List<Step> steps = usersServiceFacade.getTask(taskId).getSteps();
        List<Step> result = new LinkedList<Step>();
        for (Step s : steps) {
            if (finished == s.isFinished() && taskId == s.getTask_id()) result.add(s);
        }
        StepsDto stepsDto = converterOfTasksAndStepsEntities.fromSteps(result);
        return ResponseBuilder.buildResponseGet(stepsDto);
    }

    @RequestMapping(value = "task/{task-id}/steps", method = RequestMethod.POST)
    public ResponseEntity<ResponseObjectDto> addStep(@PathVariable("task-id") int taskId, @RequestBody StepDto stepDto)
    {
        Task task = usersServiceFacade.getTask(taskId);
        List<Step> steps = task.getSteps();
        Step step = converterOfTasksAndStepsEntities.toStep(stepDto);
        steps.add(step);
        return ResponseBuilder.buildResponseGet(stepDto);
    }

    @RequestMapping(value = "task/{task-id}/steps/{step-id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseObjectDto> updateStep(@PathVariable("task-id") int taskId,
                                                        @PathVariable ("step-id") int stepId,@RequestBody StepDto stepDto)
    {
        List<Step> steps = usersServiceFacade.getTask(taskId).getSteps();
        Step step;
        for(int i=0; i< steps.size(); i++){
            if (stepId == steps.get(i).getId() && taskId == steps.get(i).getTask_id()) {
                steps.remove(i);
                step = converterOfTasksAndStepsEntities.toStep(stepDto);
                steps.add(i, step);
            }
        }
        return ResponseBuilder.buildResponseGet(stepDto);
    }

    @RequestMapping(value = "task/{task-id}/steps/{step-id}.json", method = RequestMethod.DELETE)
    public ResponseEntity.BodyBuilder removeStep(@PathVariable("task-id") int taskId, @PathVariable ("step-id") int stepId)
    {
        List<Step> steps = usersServiceFacade.getTask(taskId).getSteps();
        for (int i=0; i < steps.size(); i++) {
            if (stepId == steps.get(i).getId() && taskId == steps.get(i).getTask_id()) steps.remove(i);
        }
        return ResponseEntity.ok();
    }
}
