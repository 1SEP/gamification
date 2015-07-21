package ru.fsep.enterprise.fseper.service.dao;

import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.User;

import java.util.List;

/**
 * Created by Marsel Sidikov and Ildar Almakayev (First Software Engineering Platform))
 */
public interface UsersDao {
    void logIn(User user);
    User getUser(int userId);
    User updateUser(User user);
    void removeUser(int userId);
    List<User> getUsers();
    List<User> getUsersByName(String firstName, String lastName);
    List<User> getUsersByPost(Post post);
    List<User> getSortedUsersByName();
    List<User> getSortedUsersByRating();
}
