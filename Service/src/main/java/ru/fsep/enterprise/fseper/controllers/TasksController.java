package ru.fsep.enterprise.fseper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacadeImpl;

/**
 * Created by Ôëþð on 13.07.2015.
 */
@RestController
public class TasksController {
    @Autowired
    private UsersServiceFacade usersServiceFacade;
    @RequestMapping(value = "/tasks/{task-id}", method = RequestMethod.GET)
    @ResponseBody
    public TaksDto assignmentTask(@PathVariable("task-id") int id)
    {
        return
    }
}
