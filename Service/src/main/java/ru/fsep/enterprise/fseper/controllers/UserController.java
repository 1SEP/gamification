package ru.fsep.enterprise.fseper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fsep.enterprise.fseper.controllers.converters.TasksAndStepsConverter;
import ru.fsep.enterprise.fseper.controllers.converters.UserConverter;
import ru.fsep.enterprise.fseper.controllers.dto.*;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.models.User;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;

import java.util.Date;
import java.util.List;

/**
 * Created by Fedorov on 14.07.2015.
 */

@RestController
//@RequestMapping(value = "user/")
public class UserController {
    @Autowired
    private UsersServiceFacade usersServiceFacade;
    @Autowired
    private TasksAndStepsConverter tasksAndStepsConverter;
    @Autowired
    private UserConverter userConverter;
    @RequestMapping(value = "{user-id}/tasks.json", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getTasks(@PathVariable("user-id") int userId)
    {
        List<Task> tasks = usersServiceFacade.getTasks(userId);
        List<TaskDto> tasksDto= tasksAndStepsConverter.fromTasks(tasks);
        return ResponseBuilder.buildResponseGet(tasksDto);
    }

    @RequestMapping(value = "{user-id}/tasks/assignments", method = RequestMethod.POST)
    public ResponseEntity<ResponseObjectDto> assignmentsTask(@RequestBody TaskDto taskDto, @PathVariable("user-id") int userId)
    {
        Task task = tasksAndStepsConverter.toTask(taskDto);
        usersServiceFacade.assignmentTask(task, userId);
        return ResponseBuilder.buildResponsePut(taskDto);
    }

    @RequestMapping(value = "{user-id}/tasks.json/filter=privated", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getPrivatedTasks(@PathVariable("user-id") int userId)
    {
        List<Task> tasks;
        tasks = usersServiceFacade.getPrivatedTasks(userId);
        List<TaskDto> tasksDto = tasksAndStepsConverter.fromTasks(tasks);
        return ResponseBuilder.buildResponseGet(tasksDto);
    }

    @RequestMapping(value = "{user-id}}/tasks.json/filter=finished", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getFinishedTasks(@PathVariable("user-id") int userId)
    {
        List<Task> tasks;
        tasks = usersServiceFacade.getFinishedTasks(userId);
        List<TaskDto> tasksDto = tasksAndStepsConverter.fromTasks(tasks);
        return ResponseBuilder.buildResponseGet(tasksDto);
    }

    @RequestMapping(value = "{user-id}/tasks.json/filter={dueDate}", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getTasksByDate(@PathVariable("dueDate") Date dueDate,
                                                            @PathVariable("user-id") int userId)
    {
        List<Task> tasks;
        tasks = usersServiceFacade.getTasksByDate(userId, dueDate);
        List<TaskDto> tasksDto = tasksAndStepsConverter.fromTasks(tasks);
        return ResponseBuilder.buildResponseGet(tasksDto);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseObjectDto> signUpUser(@RequestBody UserDto userDto){
        User entity = userConverter.toUser(userDto);
        usersServiceFacade.signUp(entity);
        return ResponseBuilder.buildResponsePut(userDto);
    }

    @RequestMapping(value = "/user/{user-id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseObjectDto> getUserById(@PathVariable("user-id") int userId){
        User user = usersServiceFacade.getUser(userId);
        UserDto userDto = userConverter.fromUser(user);
        return ResponseBuilder.buildResponseGet(userDto);
    }

    @RequestMapping(value = "{user-id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseObjectDto>  updateUserById(@RequestBody UserDto userDto, @PathVariable("user-id") int userId){
        User user = userConverter.toUser(userDto);
        usersServiceFacade.updateUser(user);
        return ResponseBuilder.buildResponseGet(userDto);
    }

    @RequestMapping(value = "{user-id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseObjectDto> deleteUserById(@PathVariable("{user-id") int userId){
        User user = usersServiceFacade.getUser(userId);
        usersServiceFacade.removeUser(userId);
        return ResponseBuilder.buildResponseGet(user.getAuthData());
    }
}
