package ru.fsep.enterprise.fseper.service.serviceFacades;

import ru.fsep.enterprise.fseper.models.User;
import ru.fsep.enterprise.fseper.service.dao.UsersDao;

import java.util.List;

/**
 * Created by Ôëþð on 07.07.2015.
 */
public class UsersServiceFacadeImpl implements UsersServiceFacade {
    private UsersDao usersDao;

    public UsersServiceFacadeImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
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

    public List<User> getUsersByName() {
        return usersDao.getUsersByName();
    }

    public List<User> getUsersByPost() {
        return usersDao.getUsersByPost();
    }

    public List<User> getSortedUsers() {
        return usersDao.getSortedUsers();
    }

    public List<User> getSortedUsersByRating() {
        return usersDao.getSortedUsersByRating();
    }
}
