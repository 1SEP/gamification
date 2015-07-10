package ru.fsep.enterprise.fseper.service.dao;

import ru.fsep.enterprise.fseper.models.User;
import ru.fsep.enterprise.fseper.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.jdbc.utils.SqlQueryExecutor;

import java.util.List;

public class UsersDaoImpl implements UsersDao {
    private SqlQueryExecutor sqlQueryExecutor;
    private ParamsMapper paramsMapper;
    private DaoArgumentsVerifier daoArgumentsVerifier;

    public void logIn(User user) {

    }

    public User getUser(int userId) {
        return null;
    }

    public User updateUser(int userId) {
        return null;
    }

    public void removeUser(int userId) {

    }

    public List<User> getUsers() {
        return null;
    }

    public List<User> getUsersByName() {
        return null;
    }

    public List<User> getUsersByPost() {
        return null;
    }

    public List<User> getSortedUsers() {
        return null;
    }

    public List<User> getSortedUsersByRating() {
        return null;
    }
}
