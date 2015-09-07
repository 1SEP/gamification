package ru.fsep.enterprise.fseper.service.facades;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.fsep.enterprise.fseper.AppContext;
import ru.fsep.enterprise.fseper.AppTestContext;
import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.Posts;

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

    @Autowired
    TasksServiceFacade tasksServiceFacade;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void partOfUserService() throws Exception {
        Posts posts;
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
        usersServiceFacade.getUsersByPost(posts.getPosts().get(1));
        usersServiceFacade.getSortedUsersByName();
        usersServiceFacade.getSortedUsersByRating();

        verify(usersServiceFacade).logIn(USER);
        verify(usersServiceFacade).signUp(USER);
        verify(usersServiceFacade).getUser(USER.getId());
        verify(usersServiceFacade).updateUser(USER);
        verify(usersServiceFacade).removeUser(USER.getId());
        verify(usersServiceFacade).getUsers();
        verify(usersServiceFacade).getUsersByName(firstName, lastName);
        verify(usersServiceFacade).getUsersByPost(posts.getPosts().get(1));
        verify(usersServiceFacade).getSortedUsersByName();
        verify(usersServiceFacade).getSortedUsersByRating();
    }

    @Test
    public void partOfTaskService() throws Exception {
        Date date = TASK_1.getDueDate();
        tasksServiceFacade.assignmentTask(TASK_1, USER.getId());
        tasksServiceFacade.getTask(TASK_1.getId());
        tasksServiceFacade.removeTask(TASK_1.getId());
        tasksServiceFacade.getTasksByPrivatedFilter(USER.getId(), true);
        tasksServiceFacade.getTasksByFinishedFilter(USER.getId(), true);
        tasksServiceFacade.getTasksByDate(USER.getId(), date);
        tasksServiceFacade.updateTask(TASK_1);
        tasksServiceFacade.getTasks(USER.getId());


        verify(tasksServiceFacade).assignmentTask(TASK_1, USER.getId());
        verify(tasksServiceFacade).getTask(TASK_1.getId());
        verify(tasksServiceFacade).removeTask(TASK_1.getId());
        verify(tasksServiceFacade).getTasksByPrivatedFilter(USER.getId(), true);
        verify(tasksServiceFacade).getTasksByFinishedFilter(USER.getId(), true);
        verify(tasksServiceFacade).getTasksByDate(USER.getId(), date);
        verify(tasksServiceFacade).updateTask(TASK_1);
        verify(tasksServiceFacade).getTasks(USER.getId());
    }

    @Test
    public void partOfPostService() throws Exception {
        Post post = USER.getPersonInfo().getPosts().getPosts().get(0);

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
        tasksServiceFacade.getSteps(TASK_1.getId());
        tasksServiceFacade.getStep(TASK_1.getId(), STEP_1_OF_TASK_1.getId());
        tasksServiceFacade.getStepsByFinishedFilter(TASK_1.getId(), true);
        tasksServiceFacade.addStep(TASK_1.getId(), STEP_1_OF_TASK_1);
        tasksServiceFacade.updateStep(TASK_1.getId(), STEP_1_OF_TASK_1);
        tasksServiceFacade.removeStep(TASK_1.getId(), STEP_1_OF_TASK_1.getId());

        verify(tasksServiceFacade).getSteps(TASK_1.getId());
        verify(tasksServiceFacade).getStep(TASK_1.getId(), STEP_1_OF_TASK_1.getId());
        verify(tasksServiceFacade).getStepsByFinishedFilter(TASK_1.getId(), true);
        verify(tasksServiceFacade).addStep(TASK_1.getId(), STEP_1_OF_TASK_1);
        verify(tasksServiceFacade).updateStep(TASK_1.getId(), STEP_1_OF_TASK_1);
        verify(tasksServiceFacade).removeStep(TASK_1.getId(), STEP_1_OF_TASK_1.getId());
    }
}
