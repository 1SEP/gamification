package ru.fsep.enterprise.fseper.service.serviceFacades;

import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.models.User;
import java.util.List;

/**
 * Created by Ôëþð on 06.07.2015.
 */
public interface UsersServiceFacade {
    void logIn(User user);
    User getUser(int userId);
    User updateUser(int userId);
    void removeUser(int userId);
    List<User> getUserList();
    List<User> getUsersByName();
    List<User> getUsersByPost();
    List<User> getSortedUsers();
    List<User> getSortedUsersByRating();
    void addPost(Post post);
    void removePost(int postId);
    void updatePost(int postId);
    List<Post> getPostList();
    void assignmentTask(Task task);
    Task getTask(int taskId);
    void updateTask(int taskId);
    void removeTask(int taskId);
    List<Task> getPrivatedTaskList();
    List<Task> getFinishedTaskList();
    List<Task> getTaskListByDate();
}
