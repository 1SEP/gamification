package ru.fsep.enterprise.fseper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.fsep.enterprise.fseper.models.Step;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ôëþð on 13.07.2015.
 */
@RestController
public class StepsController {
    @Autowired
    UsersServiceFacade usersServiceFacade;
    @RequestMapping(value = "/tasks/{task-id}/steps", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getSteps(@PathVariable("task-id") int id)
    {
        List<Step> steps = usersServiceFacade.getTask(id).getSteps();
        return ResponseBuilder.buildResponseGet(steps);
    }

    @RequestMapping(value = "task/{task-id}/steps/{step-id}.json", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getStep(@PathVariable("task-id") int taskId, @PathVariable ("step-id") int stepId)
    {
        List<Step> steps = usersServiceFacade.getTask(taskId).getSteps();
        Step step = null;
        for (Step s : steps) {
            if (stepId == s.getId()) step = s;
        }
        return ResponseBuilder.buildResponseGet(step);
    }

    @RequestMapping(value = "task/{task-id}/steps.json/filter={finished}", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getStepByFilter(@PathVariable("task-id") int taskId, @PathVariable("finished") boolean finished)
    {
        List<Step> steps = usersServiceFacade.getTask(taskId).getSteps();
        Step step = null;
        for (Step s : steps) {
            if (finished == s.isFinished()) step = s;
        }
        return ResponseBuilder.buildResponseGet(step);
    }

    @RequestMapping(value = "task/{task-id}/steps", method = RequestMethod.POST)
    public ResponseEntity<ResponseObjectDto> addStep(@PathVariable("task-id") int taskId, Step step)
    {
        Task task = usersServiceFacade.getTask(taskId);
        List<Step> steps = task.getSteps();
        steps.add(step);
        return ResponseBuilder.buildResponseGet(step);
    }

    @RequestMapping(value = "task/{task-id}/steps/{step-id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseObjectDto> updateStep(@PathVariable("task-id") int taskId, @PathVariable ("step-id") int stepId, Step step)
    {
        List<Step> steps = usersServiceFacade.getTask(taskId).getSteps();
        for(int i=0; i< steps.size(); i++){
            if (stepId == steps.get(i).getId()) {
                steps.remove(i);
                steps.add(i, step);
            }
        }
        return ResponseBuilder.buildResponseGet(step);
    }

    @RequestMapping(value = "task/{task-id}/steps/{step-id}.json", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseObjectDto> removeStep(@PathVariable("task-id") int taskId, @PathVariable ("step-id") int stepId)
    {
        List<Step> steps = usersServiceFacade.getTask(taskId).getSteps();
        Step step = null;
        for (int i=0; i < steps.size(); i++) {
            if (stepId == steps.get(i).getId()) steps.remove(i);
        }
        return ResponseBuilder.buildResponseGet(step);
    }
}
