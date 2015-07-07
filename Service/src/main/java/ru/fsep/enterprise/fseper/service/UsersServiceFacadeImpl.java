package ru.fsep.enterprise.fseper.service;

import ru.fsep.enterprise.fseper.models.User;

import java.util.ArrayList;

/**
 * Created by Ôëþð on 07.07.2015.
 */
public class UsersServiceFacadeImpl implements UsersServiceFacade {
    UsersDAO usersDAO;
    public void addUser(User user) {
        usersDAO.addUser(user);
    }

    public User getUser(String id) {
        return usersDAO.getUser(id);
    }

    public User updateUser(String id) {
        return usersDAO.updateUser(id);
    }

    public void removeUser(String id) {
        usersDAO.updateUser(id);
    }

    public ArrayList<User> getUserList() {
        return usersDAO.getUsersList();
    }
}
