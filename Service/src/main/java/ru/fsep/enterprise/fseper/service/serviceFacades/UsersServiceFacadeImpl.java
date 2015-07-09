package ru.fsep.enterprise.fseper.service.serviceFacades;

import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.models.User;
import ru.fsep.enterprise.fseper.service.dao.PostDao;
import ru.fsep.enterprise.fseper.service.dao.TasksDao;
import ru.fsep.enterprise.fseper.service.dao.UsersDao;

import java.util.List;

/**
 * Created by Ôëþð on 07.07.2015.
 */
public class UsersServiceFacadeImpl implements UsersServiceFacade {
    private UsersDao usersDao;
    private PostDao postDao;
    private TasksDao tasksDao;

    public UsersServiceFacadeImpl(UsersDao usersDao, PostDao postDao, TasksDao tasksDao) {
        this.usersDao = usersDao;
        this.postDao = postDao;
        this.tasksDao = tasksDao;
    }

    public void logIn(User user) {
        usersDao.logIn(user);
    }

    public User getUser(int userId) {
        return usersDao.getUser(userId);
    }

    public User updateUser(int userId) {
        return usersDao.updateUser(userId);
    }

    public void removeUser(int userId) {
        usersDao.removeUser(userId);
    }

    public List<User> getUserList() {
        return usersDao.getUsersList();
    }

    public List<User> getUsersByName() {
        return usersDao.getUsersByName();
    }

    public List<User> getUsersByPost() {
        return usersDao.getUsersByPost();
    }

    public List<User> getSortedUsers() {
        return usersDao.getSortedUsers();
    }

    public List<User> getSortedUsersByRating() {
        return usersDao.getSortedUsersByRating();
    }

    public void addPost(Post post) {
        postDao.addPost(post);
    }

    public void removePost(int postId) {
        postDao.removePost(postId);
    }

    public void updatePost(int postId) {
        postDao.updatePost(postId);
    }

    public List<Post> getPostList() {
        return postDao.getPostList();
    }

    public void assignmentTask(Task task) {
        tasksDao.assignmentTask(task);
    }

    public Task getTask(int taskId) {
        return tasksDao.getTask(taskId);
    }

    public void updateTask(int taskId) {
        tasksDao.updateTask(taskId);
    }

    public void removeTask(int taskId) {
        tasksDao.removeTask(taskId);
    }

    public List<Task> getPrivatedTaskList() {
        return tasksDao.getPrivatedTaskList();
    }

    public List<Task> getFinishedTaskList() {
        return tasksDao.getFinishedTaskList();
    }

    public List<Task> getTaskListByDate() {
        return tasksDao.getTaskListByDate();
    }
}
