package ru.fsep.enterprise.fseper.service.dao;

import org.mockito.Mock;
import ru.fsep.enterprise.fseper.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.jdbc.utils.SqlQueryExecutor;

import static org.junit.Assert.assertEquals;

/**
 * Created by ramil on 13.07.2015.
 */
public class TasksDaoImplTest<T> {

    @Mock
    private SqlQueryExecutor sqlQueryExecutorMock;
    @Mock
    private ParamsMapper paramsMapperMock;
    @Mock
    private DaoArgumentsVerifier daoArgumentsVerifierMock;

    private TasksDaoImpl taskDaoImplTest;


    private void stubbingDaoArgumentsVerifierMock() {
//        doThrow(IllegalArgumentException.class).when(daoArgumentsVerifierMock).verifyTask(anyInt());
//        doNothing().when(daoArgumentsVerifierMock).verifyTask(TASK_ID);
//        doNothing().when(daoArgumentsVerifierMock).verifyTask(USER_ID);
//    }
//
//    private void stubbingParamsMapperMock() {
//        doReturn(TASK_MAP).when(paramsMapperMock).asMap(asList("taskId"), asList(TASK_ID));
//        doReturn(TASK_MAP).when(paramsMapperMock).asMap(asList("userId"), asList(USER_ID));
//        doReturn(TASK_MAP).when(paramsMapperMock).asMap(asList("taskId", "due_data"), asList(TASK_ID, DATE_TASK));
//    }
//
//    private void stubbingSqlQueryExecutorMock() {
//        doReturn(TASK).when(sqlQueryExecutorMock).queryForObject(TasksDaoImpl.SQL_GET_TASK_BY_ID, TASK_MAP,
//                TasksDaoImpl.USER_ROW_MAPPER);
//        doReturn(TASK_ID).when(sqlQueryExecutorMock).queryForInt(TasksDaoImpl.SQL_GET_TASKS_BY_ID, TASK_MAP);
//        //void methods
//       // doReturn(TASK).when(sqlQueryExecutorMock).updateQuery(TasksDaoImpl.SQL_UPDATE_TASK, TASK_MAP);
//        doReturn(TASK).when(sqlQueryExecutorMock).queryForObject(TasksDaoImpl.SQL_DELETE_FROM_TASK_BY_IDS, TASK_MAP,
//                TasksDaoImpl.USER_ROW_MAPPER);
//        doReturn(TASK_LIST).when(sqlQueryExecutorMock).queryForObjects(TasksDaoImpl.SQL_GET_TASK_BY_DATE, TASK_MAP,
//                TasksDaoImpl.USER_ROW_MAPPER);
//        doReturn(TASK_LIST).when(sqlQueryExecutorMock).queryForObjects(TasksDaoImpl.SQL_INSERT_INTO_TASK, TASK_MAP,
//                TasksDaoImpl.USER_ROW_MAPPER);
//        doReturn(PRIVATED_TASK).when(sqlQueryExecutorMock).queryForObjects(TasksDaoImpl.SQL_GET_PRIVATED, TASK_MAP,
//                TasksDaoImpl.USER_ROW_MAPPER);
//        doReturn(FINISHED_TASK).when(sqlQueryExecutorMock).queryForObjects(TasksDaoImpl.SQL_GET_FINISHED, TASK_MAP,
//                TasksDaoImpl.USER_ROW_MAPPER);
//
//    }
//
//    private void stubbingAll() {
//        stubbingDaoArgumentsVerifierMock();;
//        stubbingParamsMapperMock();
//        stubbingSqlQueryExecutorMock();
//    }
//
//    @Before
//    public void setUp() throws Exception {
//        sqlQueryExecutorMock = mock(SqlQueryExecutor.class);
//        paramsMapperMock = mock(ParamsMapper.class);
//        daoArgumentsVerifierMock = mock(DaoArgumentsVerifier.class);
//        stubbingAll();
//        taskDaoImplTest = new TasksDaoImpl(daoArgumentsVerifierMock, paramsMapperMock, sqlQueryExecutorMock);
//    }
//
//    @Test
//    public void testGetTask() throws Exception {
//        Task actual = taskDaoImplTest.getTask(TASK_ID);
//        Task expected = TASK;
//        verify(daoArgumentsVerifierMock).verifyTask(TASK_ID);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testUpdateTask() throws Exception {
//        Task actual = taskDaoImplTest.updateTask(TASK);
//        Task expected = TASK;
//        verify(daoArgumentsVerifierMock).verifyTask(TASK_ID);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testRemoveTask() throws Exception {
//        taskDaoImplTest.removeTask(TASK_ID);
//        verify(daoArgumentsVerifierMock).verifyTask(TASK_ID);
//    }
//
//    private static List<Task> getListTaskDate() {
//        List<Task> result = new ArrayList<Task>();
//        result.add(TASK);
//        return result;
//    }
//
//    @Test
//    public void testGetTasksByDate() throws Exception {
//        List<Task> actual = taskDaoImplTest.getTasksByDate(TASK_ID, DATE_TASK);
//        List<Task> expected = new ArrayList<Task>(TASK_LIST);
//        verify(daoArgumentsVerifierMock).verifyTask(TASK_ID);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testAssignmentTask() {
//        taskDaoImplTest.assignmentTask(TASK, TASK_ID);
//        verify(daoArgumentsVerifierMock).verifyTask(TASK_ID);
//    }
//
//    @Test
//    public void testGetPrivatedTasks() throws Exception {
//        List<Task> actual = taskDaoImplTest.getPrivatedTasks(TASK_ID);
//        List<Task> expected = new ArrayList<Task>(PRIVATED_TASK);
//        verify(daoArgumentsVerifierMock).verifyTask(TASK_ID);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testGetFinishedTasks() throws Exception {
//        List<Task> actual = taskDaoImplTest.getFinishedTasks(TASK_ID);
//        List<Task> expected = new ArrayList<Task>(FINISHED_TASK);
//        verify(daoArgumentsVerifierMock).verifyTask(TASK_ID);
//        assertEquals(expected, actual);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void incorrect_testGetFinishedTasks() throws Exception {
//        List<Task> actual = taskDaoImplTest.getFinishedTasks(INCORRECT_TASK_ID);
//        List<Task> expected = new ArrayList<Task>(FINISHED_TASK);
//        verify(daoArgumentsVerifierMock).verifyTask(TASK_ID);
//        assertEquals(expected, actual);
    }
/*
    @Test(expected = IllegalArgumentException.class)
    public void testGetUserForIllegalUserId() throws Exception {
        taskDaoImplTest.getTask(TASK_ID);
    }
    */
}










