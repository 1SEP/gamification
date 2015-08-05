package ru.fsep.enterprise.fseper.service.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
public class UsersServiceFacadeImpl implements UsersServiceFacade {
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private PostsDao postsDao;
//    @Autowired
    private TasksDao tasksDao;

    public void signIn(User user) {
        usersDao.logIn(user);
    }

    public void signUp(User user){
        usersDao.signUp(user);
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


    public List<User> getSortedUsers() {
        return usersDao.getSortedUsersByName();
    }

    public List<User> getSortedUsersByRating() {
        return usersDao.getSortedUsersByRating();
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

    public List<Task> getTasks(int userId) {
        return tasksDao.getTasks(userId);
    }

    public Task updateTask(Task task) {
        return tasksDao.updateTask(task);
    }

    public void removeTask(int taskId) {
        tasksDao.removeTask(taskId);
    }

    public List<Task> getPrivatedTasks(int userId) {
        return tasksDao.getPrivatedTasks(userId);
    }

    public List<Task> getFinishedTasks(int userId) {
        return tasksDao.getFinishedTasks(userId);
    }

    public List<Task> getTasksByDate(int usersId, Date date) {
        return tasksDao.getTasksByDate(usersId, date);
    }


}
