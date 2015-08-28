package ru.fsep.enterprise.fseper.service.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.User;
import ru.fsep.enterprise.fseper.service.dao.PostsDao;
import ru.fsep.enterprise.fseper.service.dao.UsersDao;

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
        return usersDao.getUsersByName(firstname, lastname);
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


}
