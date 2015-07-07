package ru.fsep.enterprise.fseper.service;

import ru.fsep.enterprise.fseper.models.User;

import java.util.List;

/**
 * Created by Ôëþð on 07.07.2015.
 */
public class UsersServiceFacadeImpl implements UsersServiceFacade {
    private UsersDao usersDao;

    public UsersServiceFacadeImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public void logIn(User user) {
        usersDao.logIn(user);
    }

    public User getUser(int userId) {
        return usersDao.getUser(userId);
    }

    public User updateUser(int userId) {
        return usersDao.updateUser(userId);
    }

    public void removeUser(int userId) {
        usersDao.removeUser(userId);
    }

    public List<User> getUserList() {
        return usersDao.getUsersList();
    }
}
