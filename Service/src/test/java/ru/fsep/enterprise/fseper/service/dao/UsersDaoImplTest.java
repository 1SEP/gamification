package ru.fsep.enterprise.fseper.service.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import ru.fsep.enterprise.fseper.models.User;
import ru.fsep.enterprise.fseper.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.jdbc.utils.SqlQueryExecutor;
import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static ru.fsep.enterprise.fseper.service.dao.TestData.*;

public class UsersDaoImplTest {

    private UsersDaoImpl usersDaoImplTest;
    @Mock
    private SqlQueryExecutor sqlQueryExecutorMock;
    @Mock
    private ParamsMapper paramsMapperMock;
    @Mock
    private DaoArgumentsVerifier daoArgumentsVerifierMock;

    private void stubbingDaoArgumentsVerifierMock() {
        doThrow(IllegalArgumentException.class).when(daoArgumentsVerifierMock).verifyUser(anyInt());
        doNothing().when(daoArgumentsVerifierMock).verifyUser(USER_ID);

        doThrow(IllegalArgumentException.class).when(daoArgumentsVerifierMock).verifyPost(anyInt());
//        doNothing().when(daoArgumentsVerifierMock).verifyPost(POST.getId());

        String firstName = USER.getPersonInfo().getFirstName();
        String lastName = USER.getPersonInfo().getLastName();
        doThrow(IllegalArgumentException.class).when(daoArgumentsVerifierMock).verifyFirstNameAndLastName(anyString(), anyString());
//        doNothing().when(daoArgumentsVerifierMock).verifyFirstNameAndLastName(firstName, lastName);
    }

    private void stubbingParamsMapperMock() {
        doReturn(USER_MAP).when(paramsMapperMock).asMap(asList("userId"), asList(USER_ID));
        doReturn(POST_MAP).when(paramsMapperMock).asMap(asList("postId", "postName", "postDescription"),
                asList(POST.getId(), POST.getName(), POST.getDescription()));
    }

    private void stubbingSqlQueryExecutorMock() {
        doReturn(USER).when(sqlQueryExecutorMock).queryForObject(UsersDaoImpl.SQL_GET_USER_BY_ID, USER_MAP,
                UsersDaoImpl.USER_ROW_MAPPER);
        doReturn(LIST_OF_USERS).when(sqlQueryExecutorMock).queryForObjects(UsersDaoImpl.SQL_GET_ALL_USERS,
                UsersDaoImpl.USER_ROW_MAPPER);
        doReturn(USER).when(sqlQueryExecutorMock).queryForObject(UsersDaoImpl.SQL_UPDATE_USER, USER_MAP,
                UsersDaoImpl.USER_ROW_MAPPER);
        doReturn(LIST_OF_USERS).when(sqlQueryExecutorMock).queryForObjects(UsersDaoImpl.SQL_GET_ALL_USERS_BY_NAME,
                USER_MAP, UsersDaoImpl.USER_ROW_MAPPER);
        doReturn(LIST_OF_USERS).when(sqlQueryExecutorMock).queryForObjects(UsersDaoImpl.SQL_GET_USERS_BY_POST,
                POST_MAP, UsersDaoImpl.USER_ROW_MAPPER);
        doReturn(LIST_OF_USERS).when(sqlQueryExecutorMock).queryForObjects(UsersDaoImpl.SQL_GET_ALL_SORTED_USERS,
                UsersDaoImpl.USER_ROW_MAPPER);
        doReturn(LIST_OF_USERS).when(sqlQueryExecutorMock).queryForObjects(UsersDaoImpl.SQL_GET_ALL_SORTED_USERS_BY_RATING,
                UsersDaoImpl.USER_ROW_MAPPER);
    }

    private void stubbingAll() {
        stubbingDaoArgumentsVerifierMock();;
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
    public void testGetUser() throws Exception {
        User actual = usersDaoImplTest.getUser(USER_ID);
        User expected = USER;
        verify(daoArgumentsVerifierMock).verifyUser(USER_ID);
        assertEquals(expected, actual);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetUserForIncorrectId() throws Exception {
        usersDaoImplTest.getUser(2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testLogIn() throws Exception {
        usersDaoImplTest.logIn(USER);
        verify(daoArgumentsVerifierMock).verifyUser(USER_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateUser() throws Exception {
        User expected = USER;
        User actual = usersDaoImplTest.updateUser(USER);
        verify(daoArgumentsVerifierMock).verifyUser(USER_ID);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveUser() throws Exception {
        usersDaoImplTest.removeUser(USER_ID);
        verify(daoArgumentsVerifierMock).verifyUser(USER_ID);
    }

    @Test
    public void testGetUsers() throws Exception {
        List<User> actual = usersDaoImplTest.getUsers();
        List<User> expected = new ArrayList<User>(LIST_OF_USERS);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUsersByName() throws Exception {
        String name = USER.getPersonInfo().getFirstName();
        String surname = USER.getPersonInfo().getLastName();
        List<User> actual = usersDaoImplTest.getUsersByName(name, surname);
        List<User> expected = LIST_OF_USERS;
        verify(daoArgumentsVerifierMock).verifyFirstNameAndLastName(name, surname);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUsersByPost() throws Exception {
        List<User> actual = usersDaoImplTest.getUsersByPost(POST);
        List<User> expected = LIST_OF_USERS;
        verify(daoArgumentsVerifierMock).verifyPost(POST.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void testGetSortedUsers() throws Exception {
        List<User> expected = LIST_OF_USERS;
        List<User> actual = usersDaoImplTest.getSortedUsers();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetSortedUsersByRating() throws Exception {
        List<User> expected = LIST_OF_USERS;
        List<User> actual = usersDaoImplTest.getSortedUsersByRating();
        assertEquals(expected, actual);
    }
}
