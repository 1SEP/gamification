package ru.fsep.enterprise.fseper.service.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fsep.enterprise.fseper.models.*;
import ru.fsep.enterprise.fseper.service.dao.PostsDao;
import ru.fsep.enterprise.fseper.service.dao.StepsDao;
import ru.fsep.enterprise.fseper.service.dao.TasksDao;
import ru.fsep.enterprise.fseper.service.dao.UsersDao;

import java.util.Date;
import java.util.List;

/**
 * Created by ���� on 07.07.2015.
 */
@Service
public class UsersServiceFacadeImpl implements UsersServiceFacade {
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private PostsDao postsDao;
    @Autowired
    private TasksDao tasksDao;
    @Autowired
    private StepsDao stepsDao;

    public void signUp(User user) {
        usersDao.signUp(user);
    }

    public void logIn(User user) {
        usersDao.logIn(user);
    }

    public User getUser(int userId) {
        return usersDao.getUser(userId);
    }

    public User updateUser(User user) {
        return usersDao.updateUser(user);
    }

    public void removeUser(int userId) {
        usersDao.removeUser(userId);
    }

    public List<User> getUsers() {
        return usersDao.getUsers();
    }

    public List<User> getUsersByName(String firstname, String lastname) {
        return usersDao.getUsersByName(firstname,lastname);
    }

    public List<User> getUsersByPost(Post post) {
        return usersDao.getUsersByPost(post);
    }

    public List<User> getSortedUsersByName() {
        return usersDao.getSortedUsersByName();
    }

    public List<User> getSortedUsersByRating() {
        return usersDao.getSortedUsersByRating();
    }

    public Steps getSteps(int taskId) {
        return stepsDao.getSteps(taskId);
    }

    public Step getStep(int taskId, int stepId) {
        return stepsDao.getStep(taskId, stepId);
    }

    public Steps getStepsByFinishedFilter(int taskId, boolean finished) {
        return stepsDao.getStepsByFinishedFilter(taskId, finished);
    }

    public void addStep(int taskId, Step step) {
        stepsDao.addStep(taskId, step);
    }

    public Step updateStep(int taskId, Step step) {
<<<<<<< HEAD
        return null;
=======
        return stepsDao.updateStep(taskId, step);
>>>>>>> 4df128e70903d1e4465a6c12cbfbfecad83b7b2b
    }

    public void removeStep(int taskId, int stepId) {
        stepsDao.removeStep(taskId, stepId);
    }

    public void addPost(Post post, int userId) {
        postsDao.addPost(post, userId);
    }

    public void removePost(int postId) {
        postsDao.removePost(postId);
    }

    public Post updatePost(Post post) {
        return postsDao.updatePost(post);
    }

    public List<Post> getPosts(int userId) {
        return postsDao.getPosts(userId);
    }

    public void assignmentTask(Task task, int userId) {
        tasksDao.assignmentTask(task, userId);
    }


    public Task getTask(int taskId) {
        return tasksDao.getTask(taskId);
    }

    public Tasks getTasks(int userId) {
        return tasksDao.getTasks(userId);
    }

    public Task updateTask(Task task) {
        return tasksDao.updateTask(task);
    }

    public void removeTask(int taskId) {
        tasksDao.removeTask(taskId);
    }

    public Tasks getTasksByPrivatedFilter(int userId, boolean privated) {
        return tasksDao.getTasksByPrivatedFilter(userId, privated);
    }

    public Tasks getTasksByFinishedFilter(int userId, boolean finished) {
        return tasksDao.getTasksByFinishedFilter(userId, finished);
    }

    public Tasks getTasksByDate(int usersId, Date date) {
        return tasksDao.getTasksByDate(usersId, date);
    }


}
