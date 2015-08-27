package ru.fsep.enterprise.fseper.service.facades;

import ru.fsep.enterprise.fseper.models.*;

import java.util.Date;
import java.util.List;

/**
 * Created by ���� on 06.07.2015.
 */

public interface UsersServiceFacade {
    void addPost(Post post, int userId);
    void removePost(int postId);
    Post updatePost(Post post);
    List<Post> getPosts(int userId);

    void assignmentTask(Task task, int taskId);
    Task getTask(int taskId);
    Task updateTask(Task task);
    void removeTask(int taskId);
    Tasks getTasks(int userId);
    Tasks getTasksByPrivatedFilter(int userId, boolean privated);
    Tasks getTasksByFinishedFilter(int userId, boolean finished);
    Tasks getTasksByDate(int usersId, Date date);

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

    Steps getSteps(int taskId);
    Step getStep(int taskId, int stepId);
    Steps getStepsByFinishedFilter(int taskId, boolean finished);
    void addStep(int taskId, Step step);
    Step updateStep(int taskId, Step step);
    void removeStep(int taskId, int stepId);
}
