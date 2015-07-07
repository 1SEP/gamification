package ru.fsep.enterprise.fseper.service;

import ru.fsep.enterprise.fseper.models.User;

import java.util.ArrayList;

/**
 * Created by Ôëþð on 06.07.2015.
 */
public interface UsersServiceFacade {
    void addUser(User user);
    User getUser(String id);
    User updateUser(String id);
    void removeUser(String id);
    ArrayList<User> getUserList();
}
