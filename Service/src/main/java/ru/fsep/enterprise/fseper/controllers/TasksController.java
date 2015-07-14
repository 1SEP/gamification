package ru.fsep.enterprise.fseper.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ���� on 13.07.2015.
 */
@RestController
public class TasksController {
    @Autowired
    private UsersServiceFacade usersServiceFacade;

    @RequestMapping(value = "tasks/{task-id}.json", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getTask(@PathVariable("task-id") int id)
    {
        Task task = usersServiceFacade.getTask(id);
        return ResponseBuilder.buildResponseGet(task);
    }

    @RequestMapping(value = "tasks.json", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getTasks()
    {
        List<Task> tasks = usersServiceFacade.getTasks();
        return ResponseBuilder.buildResponseGet(tasks);
    }

    @RequestMapping(value = "tasks/assignments", method = RequestMethod.POST)
    public ResponseEntity<ResponseObjectDto> assignmentsTask(Task task, int userId)
    {
        usersServiceFacade.assignmentTask(task, userId);
        return ResponseBuilder.buildResponsePut(task);
    }

    @RequestMapping(value = "tasks/{task-id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseObjectDto> updateTask(Task task)
    {
        usersServiceFacade.updateTask(task);
        return ResponseBuilder.buildResponsePut(task);
    }

    @RequestMapping(value = "tasks/{task-id}", method = RequestMethod.DELETE)
    public ResponseEntity.BodyBuilder removeTask(@PathVariable("task-id") int taskId)
    {
        usersServiceFacade.removeTask(taskId);
        return ResponseEntity.ok();
    }

    @RequestMapping(value = "tasks.json/filter=privated", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getPrivatedTasks()
    {
        List<Task> tasks;
        tasks = usersServiceFacade.getPrivatedTasks();
        return ResponseBuilder.buildResponseGet(tasks);
    }

    @RequestMapping(value = "tasks.json/filter=finished", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getFinishedTasks()
    {
        List<Task> tasks;
        tasks = usersServiceFacade.getFinishedTasks();
        return ResponseBuilder.buildResponseGet(tasks);
    }

    @RequestMapping(value = "tasks.json/filter={dueDate}", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getTasksByDate(@PathVariable("dueDate") Date dueDate)
    {
        List<Task> tasks;
        tasks = usersServiceFacade.getTasksByDate(dueDate);
        return ResponseBuilder.buildResponseGet(tasks);
    }
}
