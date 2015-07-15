package ru.fsep.enterprise.fseper.controllers;

import com.inspiresoftware.lib.dto.geda.adapter.ValueConverter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fsep.enterprise.fseper.controllers.converters.EntityConverter;
import ru.fsep.enterprise.fseper.controllers.converters.EntityConverterImpl;
import ru.fsep.enterprise.fseper.controllers.dto.TaskDto;
import ru.fsep.enterprise.fseper.controllers.dto.TasksDto;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ôëþð on 13.07.2015.
 */
@RestController
public class TasksController {
    @Autowired
    private UsersServiceFacade usersServiceFacade;
    @Autowired
    private EntityConverter entityConverter;
    @RequestMapping(value = "tasks/{task-id}.json", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getTask(@PathVariable("task-id") int id)
    {
        Task task = usersServiceFacade.getTask(id);
        TaskDto taskDto = entityConverter.fromTask(task);
        return ResponseBuilder.buildResponseGet(taskDto);
    }

    @RequestMapping(value = "users/{users-id}/tasks.json", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getTasks(@PathVariable("users-id") int userId)
    {
        List<Task> tasks = usersServiceFacade.getTasks(userId);
        TasksDto tasksDto = entityConverter.fromTasks(tasks);
        return ResponseBuilder.buildResponseGet(tasksDto);
    }

    @RequestMapping(value = "users/{user-id}/tasks/assignments", method = RequestMethod.POST)
    public ResponseEntity<ResponseObjectDto> assignmentsTask(@RequestBody TaskDto taskDto, @PathVariable("user-id") int userId)
    {
        Task task = null;
        usersServiceFacade.assignmentTask( task, userId);
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

    @RequestMapping(value = "users/{user-id}/tasks.json/filter=privated", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getPrivatedTasks(@PathVariable("user-id") int userId)
    {
        List<Task> tasks;
        tasks = usersServiceFacade.getPrivatedTasks(userId);
        return ResponseBuilder.buildResponseGet(tasks);
    }

    @RequestMapping(value = "users/{user-id}}/tasks.json/filter=finished", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getFinishedTasks(@PathVariable("user-id") int userId)
    {
        List<Task> tasks;
        tasks = usersServiceFacade.getFinishedTasks(userId);
        return ResponseBuilder.buildResponseGet(tasks);
    }
//
//    @RequestMapping(value = "tasks.json/filter={dueDate}", method = RequestMethod.GET)
//    public ResponseEntity<ResponseObjectDto> getTasksByDate(@PathVariable("dueDate") Date dueDate)
//    {
//        List<Task> tasks;
//        tasks = usersServiceFacade.getTasksByDate(dueDate);
//        return ResponseBuilder.buildResponseGet(tasks);
//    }
}
