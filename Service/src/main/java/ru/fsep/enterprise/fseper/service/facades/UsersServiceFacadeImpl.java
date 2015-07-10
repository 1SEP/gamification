package ru.fsep.enterprise.fseper.service.facades;

import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.models.User;
import ru.fsep.enterprise.fseper.service.dao.PostsDao;
import ru.fsep.enterprise.fseper.service.dao.TasksDao;
import ru.fsep.enterprise.fseper.service.dao.UsersDao;

import java.util.Date;
import java.util.List;

/**
 * Created by Ôëþð on 07.07.2015.
 */
public class UsersServiceFacadeImpl implements UsersServiceFacade {
    private UsersDao usersDao;
    private PostsDao postsDao;
    private TasksDao tasksDao;

    public UsersServiceFacadeImpl(UsersDao usersDao, PostsDao postsDao, TasksDao tasksDao) {
        this.usersDao = usersDao;
        this.postsDao = postsDao;
        this.tasksDao = tasksDao;
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

    public List<User> getUsersByName(String firstname, String lastname, String patronymic) {
        return usersDao.getUsersByName(firstname,lastname, patronymic);
    }

    public List<User> getUsersByPost(Post post) {
        return usersDao.getUsersByPost(post);
    }


    public List<User> getSortedUsers() {
        return usersDao.getSortedUsers();
    }

    public List<User> getSortedUsersByRating() {
        return usersDao.getSortedUsersByRating();
    }

    public void addPost(Post post) {
        postsDao.addPost(post);
    }

    public void removePost(int postId) {
        postsDao.removePost(postId);
    }

    public void updatePost(Post post) {
        postsDao.updatePost(post);
    }

    public List<Post> getPosts() {
        return postsDao.getPosts();
    }

    public void assignmentTask(Task task, int userId) {
        tasksDao.assignmentTask(task, userId);
    }


    public Task getTask(int taskId) {
        return tasksDao.getTask(taskId);
    }

    public void updateTask(Task task) {
        tasksDao.updateTask(task);
    }

    public void removeTask(int taskId) {
        tasksDao.removeTask(taskId);
    }

    public List<Task> getPrivatedTasks() {
        return tasksDao.getPrivatedTasks();
    }

    public List<Task> getFinishedTasks() {
        return tasksDao.getFinishedTasks();
    }

    public List<Task> getTasksByDate(Date date) {
        return tasksDao.getTasksByDate(date);
    }


}
