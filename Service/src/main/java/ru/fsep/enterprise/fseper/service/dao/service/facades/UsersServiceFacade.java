package ru.fsep.enterprise.fseper.service.dao.service.facades;

import ru.fsep.enterprise.fseper.service.dao.models.Post;
import ru.fsep.enterprise.fseper.service.dao.models.Task;
import ru.fsep.enterprise.fseper.service.dao.models.User;

import java.util.List;

/**
 * Created by Ôëþð on 06.07.2015.
 */
public interface UsersServiceFacade {
    void logIn(User user);
    User getUser(int userId);
    User updateUser(int userId);
    void removeUser(int userId);
    List<User> getUsers();
    List<User> getUsersByName();
    List<User> getUsersByPost();
    List<User> getSortedUsers();
    List<User> getSortedUsersByRating();
    void addPost(Post post);
    void removePost(int postId);
    void updatePost(int postId);
    List<Post> getPosts();
    void assignmentTask(Task task, int userId);
    Task getTask(int taskId);
    void updateTask(int taskId);
    void removeTask(int taskId);
    List<Task> getPrivatedTasks();
    List<Task> getFinishedTasks();
    List<Task> getTasksByDate();
}
