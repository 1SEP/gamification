package ru.fsep.enterprise.fseper.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
import ru.fsep.enterprise.fseper.controllers.converters.TasksAndStepsConverter;
=======
import ru.fsep.enterprise.fseper.controllers.converters.ConverterOfTasksAndStepsEntities;
import ru.fsep.enterprise.fseper.controllers.dto.ResponseObjectDto;
>>>>>>> 76281bb412d7a39f4a7b894191fe41963867097e
import ru.fsep.enterprise.fseper.controllers.dto.TaskDto;
import ru.fsep.enterprise.fseper.controllers.dto.TasksDto;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;

import java.util.Date;
import java.util.List;

/**
 * Created by ���� on 13.07.2015.
 */
@RestController
public class TasksController {
    @Autowired
    private UsersServiceFacade usersServiceFacade;
    @Autowired
<<<<<<< HEAD
    private TasksAndStepsConverter tasksAndStepsConverter;
=======
    private ConverterOfTasksAndStepsEntities converterOfTasksAndStepsEntities;
>>>>>>> 76281bb412d7a39f4a7b894191fe41963867097e
    @RequestMapping(value = "tasks/{task-id}.json", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getTask(@PathVariable("task-id") int id)
    {
        Task task = usersServiceFacade.getTask(id);
<<<<<<< HEAD
        TaskDto taskDto = tasksAndStepsConverter.fromTask(task);
=======
        TaskDto taskDto = converterOfTasksAndStepsEntities.fromTask(task);
>>>>>>> 76281bb412d7a39f4a7b894191fe41963867097e
        return ResponseBuilder.buildResponseGet(taskDto);
    }

    @RequestMapping(value = "users/{users-id}/tasks.json", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getTasks(@PathVariable("users-id") int userId)
    {
        List<Task> tasks = usersServiceFacade.getTasks(userId);
<<<<<<< HEAD
        TasksDto tasksDto = tasksAndStepsConverter.fromTasks(tasks);
=======
        TasksDto tasksDto = converterOfTasksAndStepsEntities.fromTasks(tasks);
>>>>>>> 76281bb412d7a39f4a7b894191fe41963867097e
        return ResponseBuilder.buildResponseGet(tasksDto);
    }

    @RequestMapping(value = "users/{user-id}/tasks/assignments", method = RequestMethod.POST)
    public ResponseEntity<ResponseObjectDto> assignmentsTask(@RequestBody TaskDto taskDto, @PathVariable("user-id") int userId)
    {
        Task task = converterOfTasksAndStepsEntities.toTask(taskDto);
        usersServiceFacade.assignmentTask(task, userId);
        return ResponseBuilder.buildResponsePut(taskDto);
    }

    @RequestMapping(value = "tasks/{task-id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseObjectDto> updateTask(@RequestBody TaskDto taskDto)
    {
        Task task = converterOfTasksAndStepsEntities.toTask(taskDto);
        usersServiceFacade.updateTask(task);
        return ResponseBuilder.buildResponsePut(taskDto);
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
        TasksDto tasksDto = converterOfTasksAndStepsEntities.fromTasks(tasks);
        return ResponseBuilder.buildResponseGet(tasksDto);
    }

    @RequestMapping(value = "users/{user-id}}/tasks.json/filter=finished", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getFinishedTasks(@PathVariable("user-id") int userId)
    {
        List<Task> tasks;
        tasks = usersServiceFacade.getFinishedTasks(userId);
        TasksDto tasksDto = converterOfTasksAndStepsEntities.fromTasks(tasks);
        return ResponseBuilder.buildResponseGet(tasksDto);
    }

    @RequestMapping(value = "users/{user-id}/tasks.json/filter={dueDate}", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getTasksByDate(@PathVariable("dueDate") Date dueDate,
                                                            @PathVariable("user-id") int userId)
    {
        List<Task> tasks;
        tasks = usersServiceFacade.getTasksByDate(userId, dueDate);
        TasksDto tasksDto = converterOfTasksAndStepsEntities.fromTasks(tasks);
        return ResponseBuilder.buildResponseGet(tasksDto);
    }
}
