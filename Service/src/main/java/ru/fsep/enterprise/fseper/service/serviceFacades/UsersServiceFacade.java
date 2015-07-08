package ru.fsep.enterprise.fseper.service.serviceFacades;

import ru.fsep.enterprise.fseper.models.User;
import java.util.List;

/**
 * Created by Ôëþð on 06.07.2015.
 */
public interface UsersServiceFacade {
    User getUser(int userId);
    User updateUser(int userId);
    void removeUser(int userId);
    List<User> getUserList();
    List<User> getUsersByName();
    List<User> getUsersByPost();
    List<User> getSortedUsers();
    List<User> getSortedUsersByRating();
}
