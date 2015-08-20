package ru.fsep.enterprise.fseper.service.facades;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.fsep.enterprise.fseper.AppTestContext;
import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.test.data.TestDataCore;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.verify;
import static ru.fsep.enterprise.fseper.test.data.TestDataCore.*;
/**
 * Created by Ôëþð on 12.07.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestContext.class})
public class UsersServiceFacadeTest {

    @Autowired
    UsersServiceFacade usersServiceFacade;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void partOfUserService() throws Exception {
        List<Post> posts;
        posts = USER.getPersonInfo().getPosts();
        String firstName = USER.getPersonInfo().getFirstName();
        String lastName = USER.getPersonInfo().getLastName();
        usersServiceFacade.signIn(USER);
        usersServiceFacade.getUser(USER.getId());
        usersServiceFacade.updateUser(USER);
        usersServiceFacade.removeUser(USER.getId());
        usersServiceFacade.getUsers();
        usersServiceFacade.getUsersByName(firstName, lastName);
        usersServiceFacade.getUsersByPost(posts.get(1));
        usersServiceFacade.getSortedUsers();
        usersServiceFacade.getSortedUsersByRating();

        verify(usersServiceFacade).signIn(USER);
        verify(usersServiceFacade).getUser(USER.getId());
        verify(usersServiceFacade).updateUser(USER);
        verify(usersServiceFacade).removeUser(USER.getId());
        verify(usersServiceFacade).getUsers();
        verify(usersServiceFacade).getUsersByName(firstName, lastName);
        verify(usersServiceFacade).getUsersByPost(posts.get(1));
        verify(usersServiceFacade).getSortedUsers();
        verify(usersServiceFacade).getSortedUsersByRating();
    }

    @Test
    public void partOfTaskService() throws Exception {
        Date date = TASK_1.getDueDate();
        usersServiceFacade.assignmentTask(TASK_1, USER.getId());
        usersServiceFacade.getTask(TASK_1.getId());
        usersServiceFacade.removeTask(TASK_1.getId());
        usersServiceFacade.getPrivatedTasks(USER.getId());
        usersServiceFacade.getFinishedTasks(USER.getId());
        usersServiceFacade.getTasksByDate(USER.getId(), date);
        usersServiceFacade.updateTask(TASK_1);
        usersServiceFacade.getTasks(USER.getId());


        verify(usersServiceFacade).assignmentTask(TASK_1, USER.getId());
        verify(usersServiceFacade).getTask(TASK_1.getId());
        verify(usersServiceFacade).removeTask(TASK_1.getId());
        verify(usersServiceFacade).getPrivatedTasks(USER.getId());
        verify(usersServiceFacade).getFinishedTasks(USER.getId());
        verify(usersServiceFacade).getTasksByDate(USER.getId(), date);
        verify(usersServiceFacade).updateTask(TASK_1);
        verify(usersServiceFacade).getTasks(USER.getId());
    }

    @Test
    public void partOfPostService() throws Exception {
        Post post = TestDataCore.USER.getPersonInfo().getPosts().get(0);

        int postId = post.getId();
        usersServiceFacade.addPost(post, USER.getId());
        usersServiceFacade.removePost(postId);
        usersServiceFacade.updatePost(post);
        usersServiceFacade.getPosts(USER.getId());

        verify(usersServiceFacade).addPost(post, USER.getId());
        verify(usersServiceFacade).removePost(postId);
        verify(usersServiceFacade).updatePost(post);
        verify(usersServiceFacade).getPosts(USER.getId());
    }
}
