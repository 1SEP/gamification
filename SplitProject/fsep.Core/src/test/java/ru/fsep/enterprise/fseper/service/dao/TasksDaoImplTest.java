package ru.fsep.enterprise.fseper.service.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.service.exceptions.TaskNotFoundException;
import ru.fsep.enterprise.fseper.service.exceptions.UserNotFoundException;
import ru.fsep.enterprise.fseper.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.jdbc.utils.SqlQueryExecutor;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static ru.fsep.enterprise.fseper.service.dao.TasksDaoImpl.*;
import static ru.fsep.enterprise.fseper.test.data.TestDataForTaskDao.*;

public class TasksDaoImplTest {

    private TasksDaoImpl taskDaoImplTest;

    @Mock
    private SqlQueryExecutor sqlQueryExecutorMock;

    @Mock
    private ParamsMapper paramsMapperMock;

    @Mock
    private DaoArgumentsVerifier daoArgumentsVerifierMock;


    private void stubbingDaoArgumentsVerifierMock() {
        doThrow(TaskNotFoundException.class).when(daoArgumentsVerifierMock).verifyTask(INCORRECT_TASK_ID);
        doThrow(UserNotFoundException.class).when(daoArgumentsVerifierMock).verifyUserById(INCORRECT_USER_ID);
        doThrow(UserNotFoundException.class).when(daoArgumentsVerifierMock).verifyAssignment(INCORRECT_USER_ID, INCORRECT_TASK_ID);
    }

    private void stubbingParamsMapperMock() {
        doReturn(TASK_MAP).when(paramsMapperMock).asMap(asList("taskId"), asList(TASK_ID));
        doReturn(TASK_MAP).when(paramsMapperMock).asMap(asList("userId"), asList(USER_ID));
        doReturn(TASK_MAP).when(paramsMapperMock).asMap(asList("taskId", "due_data"), asList(TASK_ID, DATE_TASK));
    }

    private void stubbingSqlQueryExecutorMock() {
        doReturn(TASK).when(sqlQueryExecutorMock).queryForObject(SQL_GET_TASK_BY_ID, TASK_MAP,
                TASK_ROW_MAPPER);
        doReturn(TASK).when(sqlQueryExecutorMock).queryForObject(SQL_DELETE_FROM_TASK_BY_ID, TASK_MAP,
                TASK_ROW_MAPPER);
        doReturn(TASK_LIST).when(sqlQueryExecutorMock).queryForObjects(SQL_GET_TASKS_BY_DATE, TASK_MAP,
                TASK_ROW_MAPPER);
        doReturn(TASK_LIST).when(sqlQueryExecutorMock).queryForObjects(SQL_INSERT_INTO_TASK, TASK_MAP,
                TASK_ROW_MAPPER);
        doReturn(PRIVATED_TASK).when(sqlQueryExecutorMock).queryForObjects(SQL_GET_PRIVATED_TASKS, TASK_MAP,
                TASK_ROW_MAPPER);
        doReturn(FINISHED_TASK).when(sqlQueryExecutorMock).queryForObjects(SQL_GET_FINISHED_TASKS, TASK_MAP,
                TASK_ROW_MAPPER);
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
        taskDaoImplTest = new TasksDaoImpl(sqlQueryExecutorMock, paramsMapperMock, daoArgumentsVerifierMock);
    }

    @Test
    public void testGetTask() throws Exception {
        Task actual = taskDaoImplTest.getTask(TASK_ID);
        Task expected = TASK;
        verify(daoArgumentsVerifierMock).verifyTask(TASK_ID);
        assertEquals(expected, actual);
    }

    @Test(expected = TaskNotFoundException.class)
    public void testGetTaskWithIncorrectUser() throws Exception {
        taskDaoImplTest.getTask(INCORRECT_TASK_ID);
    }

    @Test
    public void testUpdateTask() throws Exception {
        Task actual = taskDaoImplTest.updateTask(TASK);
        Task expected = TASK;
        verify(daoArgumentsVerifierMock).verifyTask(TASK_ID);
        assertEquals(expected, actual);
    }

    @Test(expected = TaskNotFoundException.class)
    public void testUpdateTaskWithIncorrectTaskId() throws Exception {
        Task task = taskDaoImplTest.updateTask(INCORRECT_TASK);
        verify(daoArgumentsVerifierMock).verifyTask(INCORRECT_TASK.getId());
    }

    @Test
    public void testRemoveTask() throws Exception {
        taskDaoImplTest.removeTask(TASK_ID);
        verify(daoArgumentsVerifierMock).verifyTask(TASK_ID);
    }

    @Test(expected = TaskNotFoundException.class)
    public void testRemoveTaskWithIncorrectTaskId() throws Exception {
        taskDaoImplTest.removeTask(INCORRECT_TASK_ID);
        verify(daoArgumentsVerifierMock).verifyTask(INCORRECT_TASK_ID);
    }

    @Test
    public void testGetTasksByDate() throws Exception {
        List<Task> actual = taskDaoImplTest.getTasksByDate(TASK_ID, DATE_TASK);
        List<Task> expected = new ArrayList<Task>(TASK_LIST);
        verify(daoArgumentsVerifierMock).verifyTask(TASK_ID);
        assertEquals(expected, actual);
    }

    @Test
    public void testAssignmentTask() {
        taskDaoImplTest.assignmentTask(TASK, USER_ID);
        verify(daoArgumentsVerifierMock).verifyAssignment(USER_ID, TASK_ID);
    }

    @Test(expected = UserNotFoundException.class)
    public void testAssignmentTaskWithIncorrect() throws Exception {
        taskDaoImplTest.assignmentTask(INCORRECT_TASK, INCORRECT_USER_ID);
        verify(daoArgumentsVerifierMock).verifyUserById(INCORRECT_USER_ID);
    }

    @Test
    public void testGetPrivateTasks() throws Exception {
        List<Task> actual = taskDaoImplTest.getPrivatedTasks(USER_ID);
        List<Task> expected = new ArrayList<Task>(PRIVATED_TASK);
        verify(daoArgumentsVerifierMock).verifyUserById(USER_ID);
        assertEquals(expected, actual);
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetPrivateTasksWithIncorrectUserId() throws Exception {
        List<Task> actual = taskDaoImplTest.getPrivatedTasks(INCORRECT_USER_ID);
        verify(daoArgumentsVerifierMock).verifyUserById(INCORRECT_USER_ID);
    }

    @Test
    public void testGetFinishedTasks() throws Exception {
        List<Task> actual = taskDaoImplTest.getFinishedTasks(USER_ID);
        List<Task> expected = new ArrayList<Task>(FINISHED_TASK);
        verify(daoArgumentsVerifierMock).verifyUserById(USER_ID);
        assertEquals(expected, actual);
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetFinishedTasksWithIncorrectUser() throws Exception {
        List<Task> actual = taskDaoImplTest.getFinishedTasks(INCORRECT_USER_ID);
        List<Task> expected = new ArrayList<Task>(FINISHED_TASK);
        verify(daoArgumentsVerifierMock).verifyUserById(INCORRECT_USER_ID);
        assertEquals(expected, actual);
    }
}