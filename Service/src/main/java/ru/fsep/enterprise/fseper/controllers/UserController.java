package ru.fsep.enterprise.fseper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fsep.enterprise.fseper.controllers.converters.ConverterOfTasksAndStepsEntities;
import ru.fsep.enterprise.fseper.controllers.dto.ResponseObjectDto;
import ru.fsep.enterprise.fseper.controllers.dto.TaskDto;
import ru.fsep.enterprise.fseper.controllers.dto.TasksDto;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;

import java.util.Date;
import java.util.List;

/**
 * Created by Fedorov on 14.07.2015.
 */

@RestController
@RequestMapping(value = "users/")
public class UserController {
    @Autowired
    UsersServiceFacade usersServiceFacade;
    @Autowired
    ConverterOfTasksAndStepsEntities converterOfTasksAndStepsEntities;
    @RequestMapping(value = "{users-id}/tasks.json", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getTasks(@PathVariable("users-id") int userId)
    {
        List<Task> tasks = usersServiceFacade.getTasks(userId);
        TasksDto tasksDto = converterOfTasksAndStepsEntities.fromTasks(tasks);
        return ResponseBuilder.buildResponseGet(tasksDto);
    }

    @RequestMapping(value = "{user-id}/tasks/assignments", method = RequestMethod.POST)
    public ResponseEntity<ResponseObjectDto> assignmentsTask(@RequestBody TaskDto taskDto, @PathVariable("user-id") int userId)
    {
        Task task = converterOfTasksAndStepsEntities.toTask(taskDto);
        usersServiceFacade.assignmentTask(task, userId);
        return ResponseBuilder.buildResponsePut(taskDto);
    }

    @RequestMapping(value = "{user-id}/tasks.json/filter=privated", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getPrivatedTasks(@PathVariable("user-id") int userId)
    {
        List<Task> tasks;
        tasks = usersServiceFacade.getPrivatedTasks(userId);
        TasksDto tasksDto = converterOfTasksAndStepsEntities.fromTasks(tasks);
        return ResponseBuilder.buildResponseGet(tasksDto);
    }

    @RequestMapping(value = "{user-id}}/tasks.json/filter=finished", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getFinishedTasks(@PathVariable("user-id") int userId)
    {
        List<Task> tasks;
        tasks = usersServiceFacade.getFinishedTasks(userId);
        TasksDto tasksDto = converterOfTasksAndStepsEntities.fromTasks(tasks);
        return ResponseBuilder.buildResponseGet(tasksDto);
    }

    @RequestMapping(value = "{user-id}/tasks.json/filter={dueDate}", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getTasksByDate(@PathVariable("dueDate") Date dueDate,
                                                            @PathVariable("user-id") int userId)
    {
        List<Task> tasks;
        tasks = usersServiceFacade.getTasksByDate(userId, dueDate);
        TasksDto tasksDto = converterOfTasksAndStepsEntities.fromTasks(tasks);
        return ResponseBuilder.buildResponseGet(tasksDto);
    }
}
