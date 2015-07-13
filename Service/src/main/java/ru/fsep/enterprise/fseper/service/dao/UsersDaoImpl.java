package ru.fsep.enterprise.fseper.service.dao;

import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.User;

import java.util.List;

/**
 * Created by Ôëþð on 13.07.2015.
 */
public class UsersDaoImpl implements UsersDao {
    public void logIn(User user) {

    }

    public User getUser(int userId) {
        return null;
    }

    public User updateUser(User user) {
        return null;
    }

    public void removeUser(int userId) {

    }

    public List<User> getUsers() {
        return null;
    }

    public List<User> getUsersByName(String firstname, String lastname) {
        return null;
    }

    public List<User> getUsersByPost(Post post) {
        return null;
    }

    public List<User> getSortedUsers() {
        return null;
    }

    public List<User> getSortedUsersByRating() {
        return null;
    }
}
