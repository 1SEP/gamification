package ru.fsep.enterprise.fseper.service.facades;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.fsep.enterprise.fseper.AppContext;
import ru.fsep.enterprise.fseper.AppTestContext;
import ru.fsep.enterprise.fseper.TestData;
import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.models.User;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Ôëþð on 12.07.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestContext.class, AppContext.class})
public class UsersServiceFacadeTest {
    User user;
    int userId;
    int taskId;
    Task task;
    @Autowired
    UsersServiceFacade usersServiceFacade;
    @Before
    public void setUp() throws Exception{
   //     usersServiceFacade = mock(UsersServiceFacadeImpl.class);
        user = TestData.USER;
        userId = user.getId();
        taskId = user.getTasks().get(0).getId();
    }
    @Test
    public void partOfUserService() throws Exception{
        List<Post> posts;
        posts = user.getPersonInfo().getPosts();
        String firstName = user.getPersonInfo().getFirstName();
        String lastName = user.getPersonInfo().getLastName();
        usersServiceFacade.logIn(user);
        usersServiceFacade.getUser(userId);
        usersServiceFacade.updateUser(user);
        usersServiceFacade.removeUser(userId);
        usersServiceFacade.getUsers();
        usersServiceFacade.getUsersByName(firstName, lastName);
        usersServiceFacade.getUsersByPost(posts.get(1));
        usersServiceFacade.getSortedUsers();
        usersServiceFacade.getSortedUsersByRating();

        verify(usersServiceFacade).logIn(user);
        verify(usersServiceFacade).getUser(userId);
        verify(usersServiceFacade).updateUser(user);
        verify(usersServiceFacade).removeUser(userId);
        verify(usersServiceFacade).getUsers();
        verify(usersServiceFacade).getUsersByName(firstName, lastName);
        verify(usersServiceFacade).getUsersByPost(posts.get(1));
        verify(usersServiceFacade).getSortedUsers();
        verify(usersServiceFacade).getSortedUsersByRating();
    }
    @Test
    public void partOfTaskService() throws  Exception{
        task = user.getTasks().get(1);
        Date date = task.getDueDate();
        usersServiceFacade.assignmentTask(task, userId);
        usersServiceFacade.getTask(taskId);
        usersServiceFacade.removeTask(taskId);
        usersServiceFacade.getPrivatedTasks(userId);
        usersServiceFacade.getFinishedTasks(userId);
        usersServiceFacade.getTasksByDate(userId, date);
        usersServiceFacade.updateTask(task);
        usersServiceFacade.getTasks(userId);


        verify(usersServiceFacade).assignmentTask(task, userId);
        verify(usersServiceFacade).getTask(taskId);
        verify(usersServiceFacade).removeTask(taskId);
        verify(usersServiceFacade).getPrivatedTasks(userId);
        verify(usersServiceFacade).getFinishedTasks(userId);
        verify(usersServiceFacade).getTasksByDate(userId, date);
        verify(usersServiceFacade).updateTask(task);
        verify(usersServiceFacade).getTasks(userId);
    }
    @Test
    public void partOfPostService() throws  Exception{
        Post post = TestData.USER.getPersonInfo().getPosts().get(0);

        int postId = post.getId();
        usersServiceFacade.addPost(post, userId);
        usersServiceFacade.removePost(postId);
        usersServiceFacade.updatePost(post);
        usersServiceFacade.getPosts(userId);

        verify(usersServiceFacade).addPost(post, userId);
        verify(usersServiceFacade).removePost(postId);
        verify(usersServiceFacade).updatePost(post);
        verify(usersServiceFacade).getPosts(userId);
    }
}
