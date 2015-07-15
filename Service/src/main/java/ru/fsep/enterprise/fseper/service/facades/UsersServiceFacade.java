package ru.fsep.enterprise.fseper.service.facades;

import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.models.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Ôëþð on 06.07.2015.
 */
public interface UsersServiceFacade {
    void logIn(User user);
    User getUser(int userId);
    User updateUser(User user);
    void removeUser(int userId);
    List<User> getUsers();
    List<User> getUsersByName(String firstname, String lastname);
    List<User> getUsersByPost(Post post);
    List<User> getSortedUsers();
    List<User> getSortedUsersByRating();
    void addPost(Post post, int userId);
    void removePost(int postId);
    Post updatePost(Post post);
    List<Post> getPosts(int userId);
    void assignmentTask(Task task, int userId);
    Task getTask(int taskId);
    Task updateTask(Task task);
    void removeTask(int taskId);
    List<Task> getTasks(int userId);
    List<Task> getPrivatedTasks(int userId);
    List<Task> getFinishedTasks(int userId);
    List<Task> getTasksByDate(int usersId, Date date);

}
