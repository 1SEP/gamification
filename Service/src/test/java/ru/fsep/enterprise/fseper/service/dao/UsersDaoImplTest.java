package ru.fsep.enterprise.fseper.service.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import ru.fsep.enterprise.fseper.service.dao.models.User;
import ru.fsep.enterprise.fseper.service.dao.service.dao.UsersDaoImpl;
import ru.fsep.enterprise.fseper.service.dao.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.dao.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.dao.service.jdbc.utils.SqlQueryExecutor;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static ru.fsep.enterprise.fseper.service.dao.TestData.USER;
import static ru.fsep.enterprise.fseper.service.dao.TestData.USER_ID;
import static ru.fsep.enterprise.fseper.service.dao.TestData.USER_MAP;

/**
 * Created by Marsel Sidikov and Ildar Almakayev (First Software Engineering Platform))
 */
public class UsersDaoImplTest {

    @Mock
    private SqlQueryExecutor sqlQueryExecutorMock;
    @Mock
    private ParamsMapper paramsMapperMock;
    @Mock
    private DaoArgumentsVerifier daoArgumentsVerifierMock;

    private UsersDaoImpl usersDaoImplTest;

    private void stubbingDaoArgumentsVerifierMock() {
        doThrow(IllegalArgumentException.class).when(daoArgumentsVerifierMock).verifyUser(anyInt());
        doNothing().when(daoArgumentsVerifierMock).verifyUser(USER_ID);
    }

    private void stubbingParamsMapperMock() {
        doReturn(USER_MAP).when(paramsMapperMock).asMap(asList("userId"), asList(USER_ID));
    }

    private void stubbingSqlQueryExecutorMock() {
        doReturn(USER).when(sqlQueryExecutorMock).queryForObject(UsersDaoImpl.SQL_GET_USER_BY_ID, USER_MAP,
                UsersDaoImpl.USER_ROW_MAPPER);
    }

    private void stubbingAll() {
        stubbingDaoArgumentsVerifierMock();;
        stubbingParamsMapperMock();
        stubbingSqlQueryExecutorMock();
    }

    @Before
    public void setUp() throws Exception {
        sqlQueryExecutorMock = mock(SqlQueryExecutor.class);
        paramsMapperMock = mock(ParamsMapper.class);
        daoArgumentsVerifierMock = mock(DaoArgumentsVerifier.class);
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

//    @Test(expected = IllegalArgumentException.class)
//    public void testGetUserForIllegalUserId() throws Exception {
//        usersDaoImplTest.getUser(20);
//    }

    @Test
    public void testLogIn() throws Exception {
        usersDaoImplTest.logIn(USER);
    }

    @Test
    public void testUpdateUser() throws Exception {

    }

    @Test
    public void testRemoveUser() throws Exception {

    }

    @Test
    public void testGetUsers() throws Exception {

    }

    @Test
    public void testGetUsersByName() throws Exception {

    }

    @Test
    public void testGetUsersByPost() throws Exception {

    }

    @Test
    public void testGetSortedUsers() throws Exception {

    }

    @Test
    public void testGetSortedUsersByRating() throws Exception {

    }
}