package ru.fsep.enterprise.fseper.service.facades;

import ru.fsep.enterprise.fseper.models.*;

import java.util.Date;
import java.util.List;

/**
 * Created by ���� on 06.07.2015.
 */

public interface UsersServiceFacade {
    void signUp(User user);
    void logIn(User user);
    User getUser(int userId);
    User updateUser(User user);
    void removeUser(int userId);
    List<User> getUsers();
    List<User> getUsersByName(String firstName, String lastName);
    List<User> getUsersByPost(Post post);
    List<User> getSortedUsersByName();
    List<User> getSortedUsersByRating();

    void addPost(Post post, int userId);
    void removePost(int postId);
    Post updatePost(Post post);
    List<Post> getPosts(int userId);
}
