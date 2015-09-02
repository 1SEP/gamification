package ru.fsep.enterprise.fseper.service.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import ru.fsep.enterprise.fseper.AppContext;
import ru.fsep.enterprise.fseper.models.User;
import ru.fsep.enterprise.fseper.service.exceptions.PostsNotFoundException;
import ru.fsep.enterprise.fseper.service.exceptions.UserNotFoundException;
import ru.fsep.enterprise.fseper.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.jdbc.utils.SqlQueryExecutor;
import ru.fsep.enterprise.fseper.test.data.TestDataForTaskDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static ru.fsep.enterprise.fseper.service.dao.TasksDaoImpl.SQL_GET_TASK_BY_ID;
import static ru.fsep.enterprise.fseper.service.dao.TasksDaoImpl.TASK_ROW_MAPPER;
import static ru.fsep.enterprise.fseper.service.dao.UsersDaoImpl.*;
import static ru.fsep.enterprise.fseper.test.data.TestDataForUserDao.*;

@ContextConfiguration(classes = {AppContext.class})
public class UsersDaoImplTest {

    private UsersDao usersDaoImplTest;
    @Mock
    private SqlQueryExecutor sqlQueryExecutorMock;
    @Mock
    private ParamsMapper paramsMapperMock;
    @Mock
    private DaoArgumentsVerifier daoArgumentsVerifierMock;

    private void stubbingDaoArgumentsVerifierMock() {
        doThrow(UserNotFoundException.class).when(daoArgumentsVerifierMock).verifyUser(INCORRECT_USER);
        doThrow(UserNotFoundException.class).when(daoArgumentsVerifierMock).verifyUserById(INCORRECT_USER_ID);
        doThrow(UserNotFoundException.class).when(daoArgumentsVerifierMock).verifyUserByName(INCORRECT_FIRSTNAME, INCORRECT_LASTNAME);
        doThrow(PostsNotFoundException.class).when(daoArgumentsVerifierMock).verifyPost(INCORRECT_POST);
        doNothing().when(daoArgumentsVerifierMock).verifyPost(POST);
    }

    private void stubbingParamsMapperMock() {
        doReturn(USER_MAP).when(paramsMapperMock).asMap(asList("userId"), asList(USER_ID));

        String lastName = USER.getPersonInfo().getLastName();
        String firstName = USER.getPersonInfo().getFirstName();
        doReturn(USER_MAP_WITH_NAMES).when(paramsMapperMock).asMap(asList("firstName", "lastName"),
                asList(firstName, lastName));

        doReturn(POST_MAP).when(paramsMapperMock).asMap(asList("postId", "postName", "postDescription"),
                asList(POST.getId(), POST.getName(), POST.getDescription()));
    }

    private void stubbingSqlQueryExecutorMock() {
        doReturn(USER).when(sqlQueryExecutorMock).queryForObject(SQL_GET_USER_BY_ID, USER_MAP,
                USER_ROW_MAPPER);
        doReturn(LIST_OF_USERS).when(sqlQueryExecutorMock).queryForObjects(SQL_GET_ALL_USERS,
                USER_ROW_MAPPER);
        doReturn(USER).when(sqlQueryExecutorMock).queryForObject(SQL_UPDATE_USER, USER_MAP,
                USER_ROW_MAPPER);
        doReturn(LIST_OF_USERS).when(sqlQueryExecutorMock).queryForObjects(SQL_GET_ALL_USERS_BY_NAME,
                USER_MAP_WITH_NAMES, USER_ROW_MAPPER);
        doReturn(LIST_OF_USERS).when(sqlQueryExecutorMock).queryForObjects(SQL_GET_USERS_BY_POST,
                POST_MAP, USER_ROW_MAPPER);
        doReturn(LIST_OF_USERS).when(sqlQueryExecutorMock).queryForObjects(SQL_GET_ALL_SORTED_USERS_BY_NAME,
                USER_ROW_MAPPER);
        doReturn(LIST_OF_USERS).when(sqlQueryExecutorMock).queryForObjects(SQL_GET_ALL_SORTED_USERS_BY_RATING,
                USER_ROW_MAPPER);
        Map<String, Object> task_map = paramsMapperMock.asMap(asList("taskId"), asList(TestDataForTaskDao.TASK_ID));
        doReturn(TestDataForTaskDao.TASK).when(sqlQueryExecutorMock).queryForObject(SQL_GET_TASK_BY_ID, task_map, TASK_ROW_MAPPER);
    }

    private void stubbingAll() {
        stubbingDaoArgumentsVerifierMock();
        stubbingParamsMapperMock();
        stubbingSqlQueryExecutorMock();
    }

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        stubbingAll();
        usersDaoImplTest = new UsersDaoImpl(sqlQueryExecutorMock, paramsMapperMock, daoArgumentsVerifierMock);
    }

    @Test
    public void testSignUp() throws Exception {
        usersDaoImplTest.signUp(USER);
        verify(daoArgumentsVerifierMock).verifyUser(USER);
    }

    @Test(expected = UserNotFoundException.class)
    public void testSignUpForIncorrectUser() {
        usersDaoImplTest.signUp(INCORRECT_USER);
    }

    @Test
    public void testGetUser() throws Exception {
        User actual = usersDaoImplTest.getUser(USER_ID);
        User expected = USER;
        verify(daoArgumentsVerifierMock, times(2)).verifyUserById(USER_ID);
        assertEquals(expected, actual);
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetUserForIncorrectId() throws Exception {
        User user = usersDaoImplTest.getUser(INCORRECT_USER_ID);
        assertNotNull(user);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User expected = USER;
        User actual = usersDaoImplTest.updateUser(USER);
        verify(daoArgumentsVerifierMock).verifyUser(USER);
        assertEquals(expected, actual);
    }

    @Test(expected = UserNotFoundException.class)
    public void testUpdateUserForIncorrectUser() {
        usersDaoImplTest.updateUser(INCORRECT_USER);
    }

    @Test
    public void testRemoveUser() throws Exception {
        usersDaoImplTest.removeUser(USER_ID);
        verify(daoArgumentsVerifierMock).verifyUserById(USER_ID);
    }

    @Test(expected = UserNotFoundException.class)
    public void testRemoveUserForIncorrectId() {
        usersDaoImplTest.removeUser(INCORRECT_USER_ID);
    }

    @Test
    public void testGetUsers() throws Exception {
        List<User> actual = usersDaoImplTest.getUsers();
        List<User> expected = new ArrayList<User>(LIST_OF_USERS);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetUsersByName() throws Exception {
        String name = USER.getPersonInfo().getFirstName();
        String surname = USER.getPersonInfo().getLastName();
        List<User> actual = usersDaoImplTest.getUsersByName(name, surname);
        List<User> expected = LIST_OF_USERS;
        verify(daoArgumentsVerifierMock).verifyUserByName(name, surname);
        assertEquals(expected, actual);
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetUsersByNameForIncorrectName() {
        usersDaoImplTest.getUsersByName(INCORRECT_FIRSTNAME, INCORRECT_LASTNAME);
    }

    @Test
    public void testGetUsersByPost() throws Exception {
        List<User> actual = usersDaoImplTest.getUsersByPost(POST);
        List<User> expected = LIST_OF_USERS;
        verify(daoArgumentsVerifierMock).verifyPost(POST);
        assertEquals(expected, actual);
    }

    @Test(expected = PostsNotFoundException.class)
    public void testGetUsersByIncorrectPost() {
        usersDaoImplTest.getUsersByPost(INCORRECT_POST);
    }

    @Test
    public void testGetSortedUsersByName() throws Exception {
        List<User> expected = LIST_OF_USERS;
        List<User> actual = usersDaoImplTest.getSortedUsersByName();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetSortedUsersByRating() throws Exception {
        List<User> expected = LIST_OF_USERS;
        List<User> actual = usersDaoImplTest.getSortedUsersByRating();
        assertEquals(expected, actual);
    }
}
