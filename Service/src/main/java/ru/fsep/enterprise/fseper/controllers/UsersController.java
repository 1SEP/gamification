package ru.fsep.enterprise.fseper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fsep.enterprise.fseper.controllers.converters.UserConverter;
import ru.fsep.enterprise.fseper.controllers.dto.ResponseObjectDto;
import ru.fsep.enterprise.fseper.controllers.dto.UsersDto;
import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.User;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;

import java.util.List;
import java.util.logging.Filter;

import static java.util.logging.Filter.*;

/**
 * Author Fedorov Juriy on 22.07.2015
 */

@RestController
@RequestMapping(value = "users/")
public class UsersController {
    @Autowired
    private UsersServiceFacade usersServiceFacade;
    @Autowired
    private UserConverter userConverter;

    @RequestMapping
    public ResponseEntity<ResponseObjectDto> getAllUsers() {
        List<User> users = usersServiceFacade.getUsers();
        UsersDto usersDto = userConverter.fromUsers(users);
        return ResponseBuilder.buildResponseGet(usersDto);
    }

    @RequestMapping(value = "by_name.json")
    public ResponseEntity<ResponseObjectDto> getUsersByName(@RequestParam("first_name") String firstName,
                                                            @RequestParam("last_name") String lastName){
        List<User> users = usersServiceFacade.getUsersByName(firstName, lastName);
        UsersDto usersDto = userConverter.fromUsers(users);
        return ResponseBuilder.buildResponseGet(usersDto);
    }

    @RequestMapping(value = "by_post.json")
    public ResponseEntity<ResponseObjectDto> getUserByPost(@RequestParam("post") String nameOfPost){
        // Id of post not found, need to do
        //TODO
        Post post = new Post(0, nameOfPost, null);
        List<User> users = usersServiceFacade.getUsersByPost(post);
        UsersDto usersDto = userConverter.fromUsers(users);
        return ResponseBuilder.buildResponseGet(usersDto);
    }

    @RequestMapping(value = "sorted_by_rating.json")
    public ResponseEntity<ResponseObjectDto> getSortedUserByRating(){
        List<User> users = usersServiceFacade.getSortedUsersByRating();
        UsersDto usersDto = userConverter.fromUsers(users);
        return ResponseBuilder.buildResponseGet(usersDto);
    }

    @RequestMapping(value = "sorted_by_abc.json")
    public ResponseEntity<ResponseObjectDto> getSortedUserByAbc(){
        List<User> users = usersServiceFacade.getSortedUsers();
        UsersDto usersDto = userConverter.fromUsers(users);
        return ResponseBuilder.buildResponseGet(usersDto);
    }
}
