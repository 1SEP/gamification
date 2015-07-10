package ru.fsep.enterprise.fseper.service.dao;

import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.User;
import java.util.List;

/**
 * Created by ���� on 06.07.2015.
 */
public interface UsersDao {
    void logIn(User user);
    User getUser(int userId);
    User updateUser(User user);
    void removeUser(int userId);
    List<User> getUsers();
    List<User> getUsersByName(String firstName, String lastName, String patronymic);
    Post getUsersByPost();
    List<User> getSortedUsers();
    List<User> getSortedUsersByRating();
}
