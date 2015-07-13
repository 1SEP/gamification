package ru.fsep.enterprise.fseper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;

/**
 * Created by Ôëþð on 13.07.2015.
 */
@RestController
public class StepsController {
    @Autowired
    UsersServiceFacade usersServiceFacade;
}
