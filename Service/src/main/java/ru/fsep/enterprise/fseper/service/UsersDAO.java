package ru.fsep.enterprise.fseper.service;

import ru.fsep.enterprise.fseper.models.User;
import java.util.List;

/**
 * Created by Ôëþð on 06.07.2015.
 */
public interface UsersDao {
    void logIn(User user);
    User getUser(String userId);
    User updateUser(String userId);
    void removeUser(String userId);
    List<User> getUsersList();
}
