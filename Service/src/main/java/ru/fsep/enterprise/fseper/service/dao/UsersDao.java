package ru.fsep.enterprise.fseper.service.dao;

import ru.fsep.enterprise.fseper.models.User;
import java.util.List;

/**
 * Created by Ôëþð on 06.07.2015.
 */
public interface UsersDao {
    User getUser(int userId);
    User updateUser(int userId);
    void removeUser(int userId);
    List<User> getUsersList();
    List<User> getUsersByName();
    List<User> getUsersByPost();
    List<User> getSortedUsers();
    List<User> getSortedUsersByRating();
}
