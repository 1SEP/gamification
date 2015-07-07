package ru.fsep.enterprise.fseper.service;

import ru.fsep.enterprise.fseper.models.User;
import java.util.List;

/**
 * Created by ���� on 06.07.2015.
 */
public interface UsersServiceFacade {
    void logIn(User user);
    User getUser(int userId);
    User updateUser(int userId);
    void removeUser(int userId);
    List<User> getUserList();
}
