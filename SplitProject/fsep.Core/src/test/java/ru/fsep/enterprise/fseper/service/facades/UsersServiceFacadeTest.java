package ru.fsep.enterprise.fseper.service.facades;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.fsep.enterprise.fseper.AppTestContext;
import ru.fsep.enterprise.fseper.models.Post;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.verify;
import static ru.fsep.enterprise.fseper.test.data.TestDataCore.*;
/**
 * Created by ���� on 12.07.2015.
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
        usersServiceFacade.logIn(USER);
        usersServiceFacade.signUp(USER);
        usersServiceFacade.getUser(USER.getId());
        usersServiceFacade.updateUser(USER);
        usersServiceFacade.removeUser(USER.getId());
        usersServiceFacade.getUsers();
        usersServiceFacade.getUsersByName(firstName, lastName);
        usersServiceFacade.getUsersByPost(posts.get(1));
        usersServiceFacade.getSortedUsersByName();
        usersServiceFacade.getSortedUsersByRating();

        verify(usersServiceFacade).logIn(USER);
        verify(usersServiceFacade).signUp(USER);
        verify(usersServiceFacade).getUser(USER.getId());
        verify(usersServiceFacade).updateUser(USER);
        verify(usersServiceFacade).removeUser(USER.getId());
        verify(usersServiceFacade).getUsers();
        verify(usersServiceFacade).getUsersByName(firstName, lastName);
        verify(usersServiceFacade).getUsersByPost(posts.get(1));
        verify(usersServiceFacade).getSortedUsersByName();
        verify(usersServiceFacade).getSortedUsersByRating();
    }

    @Test
    public void partOfTaskService() throws Exception {
        Date date = TASK_1.getDueDate();
        usersServiceFacade.assignmentTask(TASK_1, USER.getId());
        usersServiceFacade.getTask(TASK_1.getId());
        usersServiceFacade.removeTask(TASK_1.getId());
        usersServiceFacade.getTasksByPrivatedFilter(USER.getId(), true);
        usersServiceFacade.getTasksByFinishedFilter(USER.getId(), true);
        usersServiceFacade.getTasksByDate(USER.getId(), date);
        usersServiceFacade.updateTask(TASK_1);
        usersServiceFacade.getTasks(USER.getId());


        verify(usersServiceFacade).assignmentTask(TASK_1, USER.getId());
        verify(usersServiceFacade).getTask(TASK_1.getId());
        verify(usersServiceFacade).removeTask(TASK_1.getId());
        verify(usersServiceFacade).getTasksByPrivatedFilter(USER.getId(), true);
        verify(usersServiceFacade).getTasksByFinishedFilter(USER.getId(), true);
        verify(usersServiceFacade).getTasksByDate(USER.getId(), date);
        verify(usersServiceFacade).updateTask(TASK_1);
        verify(usersServiceFacade).getTasks(USER.getId());
    }

    @Test
    public void partOfPostService() throws Exception {
        Post post = USER.getPersonInfo().getPosts().get(0);

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
    @Test
    public void partOfStepSeervice() throws Exception{
        usersServiceFacade.getSteps(TASK_1.getId());
        usersServiceFacade.getStep(TASK_1.getId(), STEP_1_OF_TASK_1.getId());
        usersServiceFacade.getStepsByFinishedFilter(TASK_1.getId(), true);
        usersServiceFacade.addStep(TASK_1.getId(), STEP_1_OF_TASK_1);
        usersServiceFacade.updateStep(TASK_1.getId(), STEP_1_OF_TASK_1);
        usersServiceFacade.removeStep(TASK_1.getId(), STEP_1_OF_TASK_1.getId());

        verify(usersServiceFacade).getSteps(TASK_1.getId());
        verify(usersServiceFacade).getStep(TASK_1.getId(), STEP_1_OF_TASK_1.getId());
        verify(usersServiceFacade).getStepsByFinishedFilter(TASK_1.getId(), true);
        verify(usersServiceFacade).addStep(TASK_1.getId(), STEP_1_OF_TASK_1);
        verify(usersServiceFacade).updateStep(TASK_1.getId(), STEP_1_OF_TASK_1);
        verify(usersServiceFacade).removeStep(TASK_1.getId(), STEP_1_OF_TASK_1.getId());
    }
}
