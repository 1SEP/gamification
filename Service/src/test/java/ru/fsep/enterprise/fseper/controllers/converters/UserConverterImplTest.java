package ru.fsep.enterprise.fseper.controllers.converters;

import org.junit.Before;
import org.junit.Test;
import ru.fsep.enterprise.fseper.TestData;
import ru.fsep.enterprise.fseper.controllers.dto.PostDto;
import ru.fsep.enterprise.fseper.controllers.dto.UserDto;
import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.User;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Author Fedorov Juriy on 22.07.2015
 */
public class UserConverterImplTest {
    UserConverter userConverter = new UserConverterImpl();
    TasksAndStepsConverter taskConverter;
    UserConverter userConverterMock;
    User user;

    @Before
    public void setUp() throws Exception {
        userConverter = new UserConverterImpl();
        taskConverter = new TasksAndStepsConverterImpl();
        user = TestData.USER;
    }

    @Test
    public void testFromPost() throws Exception {
        Post post = user.getPersonInfo().getPosts().get(0);
        PostDto expected = new PostDto("1", "It director", "He creates new steps of company development in IT");
        PostDto actual = userConverter.fromPost(post);
        assertEquals(expected, actual);
    }

    @Test
    public void testFromPosts() throws Exception {

    }

    @Test
    public void testFromPersonInfo() throws Exception {


    }

    @Test
    public void testFromUser() throws Exception {
        User user = TestData.USER;
        userConverterMock = mock(UserConverter.class);
        taskConverter = mock(TasksAndStepsConverter.class);

//        when(userConverterMock.fromPersonInfo(user.getPersonInfo())).thenReturn(TestData.initPersonInfoDto());
//        when(taskConverter.fromTasks(user.getTasks())).thenReturn(TestData.initTasksDto());

        UserDto actual;
        actual = userConverter.fromUser(user);
        UserDto expected = TestData.initUserDto();
        assertEquals(expected, actual);

    }

    @Test
    public void testFromUsers() throws Exception {

    }

    @Test
    public void testToPost() throws Exception {

    }

    @Test
    public void testToPosts() throws Exception {

    }

    @Test
    public void testToPersonInfo() throws Exception {

    }

    @Test
    public void testToUser() throws Exception {

    }

    @Test
    public void testToUsers() throws Exception {

    }
}