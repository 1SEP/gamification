package ru.fsep.enterprise.fseper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fsep.enterprise.fseper.controllers.converters.TasksAndStepsConverter;
import ru.fsep.enterprise.fseper.controllers.converters.UserConverter;
import ru.fsep.enterprise.fseper.controllers.dto.ResponseDto;
import ru.fsep.enterprise.fseper.controllers.dto.TaskDto;
import ru.fsep.enterprise.fseper.controllers.dto.UserDto;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.models.Tasks;
import ru.fsep.enterprise.fseper.models.User;
import ru.fsep.enterprise.fseper.service.facades.TasksServiceFacade;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;

import java.util.Date;
import java.util.List;

/**
 * Created by Fedorov on 14.07.2015.
 */

@RestController
@RequestMapping(value = "/user/")
public class UserController {
    @Autowired
    private UsersServiceFacade usersServiceFacade;

    @Autowired
    private TasksServiceFacade tasksServiceFacade;
    @Autowired
    private TasksAndStepsConverter tasksAndStepsConverter;

    @Autowired
    private UserConverter userConverter;

    @RequestMapping(value = "{user-id}/tasks.json", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto> getTasks(@PathVariable("user-id") int userId)
    {
        Tasks tasks = tasksServiceFacade.getTasks(userId);
        List<TaskDto> tasksDto = tasksAndStepsConverter.fromTasks(tasks);
        return ResponseBuilder.buildResponseGet(tasksDto);
    }

    @RequestMapping(value = "{user-id}/tasks/assignments", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> assignmentsTask(@RequestBody TaskDto taskDto, @PathVariable("user-id") int userId)
    {
        Task task = tasksAndStepsConverter.toTask(taskDto);
        tasksServiceFacade.assignmentTask(task, userId);
        return ResponseBuilder.buildResponsePut(taskDto);
    }

    @RequestMapping(value = "{user-id}/tasks.json/filter={privated}", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto> getTasksByPrivatedFilter(@PathVariable("user-id") int userId,
                                                                @PathVariable("privated") boolean privated)
    {
        Tasks tasks = tasksServiceFacade.getTasksByPrivatedFilter(userId, privated);
        List<TaskDto> tasksDto = tasksAndStepsConverter.fromTasks(tasks);
        return ResponseBuilder.buildResponseGet(tasksDto);
    }

    @RequestMapping(value = "{user-id}}/tasks.json/filter={finished}", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto> getTasksByFinishedFilter(@PathVariable("user-id") int userId,
                                                                @PathVariable("finished") boolean finished)
    {
        Tasks tasks = tasksServiceFacade.getTasksByFinishedFilter(userId, finished);
        List<TaskDto> tasksDto = tasksAndStepsConverter.fromTasks(tasks);
        return ResponseBuilder.buildResponseGet(tasksDto);
    }

    @RequestMapping(value = "{user-id}/tasks.json/filter={dueDate}", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto> getTasksByDate(@PathVariable("dueDate") Date dueDate,
                                                            @PathVariable("user-id") int userId)
    {
        Tasks tasks = tasksServiceFacade.getTasksByDate(userId, dueDate);
        List<TaskDto> tasksDto = tasksAndStepsConverter.fromTasks(tasks);
        return ResponseBuilder.buildResponseGet(tasksDto);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> signUpUser(@RequestBody UserDto userDto){
        User entity = userConverter.toUser(userDto);
        usersServiceFacade.signUp(entity);
        return ResponseBuilder.buildResponsePut(userDto);
    }

    @RequestMapping(value = "{user-id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto> getUserById(@PathVariable("user-id") int userId){
        User outUser = usersServiceFacade.getUser(userId);
        UserDto outUserDto = userConverter.fromUser(outUser);
        return ResponseBuilder.buildResponseGet(outUserDto);
    }

    @RequestMapping(value = "{user-id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseDto>  updateUserById(@RequestBody UserDto userDto, @PathVariable("user-id") int userId){
        User user = userConverter.toUser(userDto);
        usersServiceFacade.updateUser(user);
        return ResponseBuilder.buildResponseGet(userDto);
    }

    @RequestMapping(value = "{user-id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDto> deleteUserById(@PathVariable("user-id") int userId){
        User user = usersServiceFacade.getUser(userId);
        usersServiceFacade.removeUser(userId);
        return ResponseBuilder.buildResponseGet(user.getAuthData());
    }
}
