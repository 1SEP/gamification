package ru.fsep.enterprise.fseper.service.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import ru.fsep.enterprise.fseper.models.Step;
import ru.fsep.enterprise.fseper.models.Steps;
import ru.fsep.enterprise.fseper.service.exceptions.TaskNotFoundException;
import ru.fsep.enterprise.fseper.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.jdbc.utils.SqlQueryExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static ru.fsep.enterprise.fseper.service.dao.StepsDaoImpl.*;
import static ru.fsep.enterprise.fseper.test.data.TestDataForTaskDao.INCORRECT_TASK_ID;
import static ru.fsep.enterprise.fseper.test.data.TestDataForTaskDao.TASK_ID;

public class StepsDaoImplTest {

    private Step testStep;
    private StepsDao stepsDao;
    private Steps testSteps;
    @Mock
    SqlQueryExecutor sqlQueryExecutor;
    @Mock
    ParamsMapper paramsMapper;
    @Mock
    DaoArgumentsVerifier argumentsVerifier;

    private void stubbingForMocks() {
        doThrow(TaskNotFoundException.class).when(argumentsVerifier).verifyTask(INCORRECT_TASK_ID);

        List<Step> testStepsList = new ArrayList<Step>();
        testStep = new Step(1, TASK_ID, "test_description", true);
        testStepsList.add(testStep);

        Map<String, Object> testParamMap = paramsMapper.asMap(asList("taskId"), asList(TASK_ID));

        doReturn(testStepsList).when(sqlQueryExecutor).queryForObjects(StepsDaoImpl.SQL_GET_STEPS_BY_TASKID, testParamMap, STEPS_ROW_MAPPER);
        doReturn(testStep).when(sqlQueryExecutor).queryForObject(SQL_GET_STEP, testParamMap, STEPS_ROW_MAPPER);
        doReturn(testStepsList).when(sqlQueryExecutor).queryForObjects(SQL_GET_ALL_FINISHED_STEPS, testParamMap, STEPS_ROW_MAPPER);

        testSteps = new Steps(testStepsList);
    }

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        stubbingForMocks();
        stepsDao = new StepsDaoImpl(sqlQueryExecutor, paramsMapper, argumentsVerifier);
    }

    @Test
    public void testGetSteps() throws Exception {
        Steps steps = stepsDao.getSteps(TASK_ID);
        assertNotNull("The reference of steps list is NULL!", steps);
    }

    @Test
    public void testGetStep() throws Exception {
        Step expected = new Step(1, TASK_ID, "test_description", true);
        int testStepId = 1;
        Step actual = stepsDao.getStep(TASK_ID, testStepId);
        verify(argumentsVerifier).verifyTask(TASK_ID);
        assertEquals(expected, actual);
    }

    @Test(expected = TaskNotFoundException.class)
    public void testGetStepIncorrectTaskId() throws Exception {
        int testStepId = 1;
        Step actual = stepsDao.getStep(INCORRECT_TASK_ID, testStepId);
    }

    @Test
    public void testAdd() throws Exception {
        Step step = new Step(1, TASK_ID, "test_description", true);
        stepsDao.addStep(TASK_ID, step);
        verify(argumentsVerifier).verifyTask(TASK_ID);
    }

    @Test(expected = TaskNotFoundException.class)
    public void testAddWithIncorrectTaskId() throws Exception {
        stepsDao.addStep(INCORRECT_TASK_ID, new Step(1, INCORRECT_TASK_ID, "test", false));
    }

    @Test
    public void testRemove() throws Exception {
        int testStepId = 1;
        stepsDao.removeStep(TASK_ID, testStepId);
        verify(argumentsVerifier).verifyTask(TASK_ID);
    }

    @Test(expected = TaskNotFoundException.class)
    public void testRemoveWithIncorrectTaskId() throws Exception {
        int testStepId = 1;
        stepsDao.removeStep(INCORRECT_TASK_ID, testStepId);
    }


    @Test
    public void testUpdate() throws Exception {
        Step testStep = new Step(1, TASK_ID, "test_description", true);
        Step actual = stepsDao.updateStep(TASK_ID, testStep);
        assertEquals(testStep, actual);
    }

    @Test(expected = TaskNotFoundException.class)
    public void testUpdateWithIncorrectTaskId() throws Exception {
        Step testStep = new Step(1, INCORRECT_TASK_ID, "test_description", true);
        Step actual = stepsDao.updateStep(INCORRECT_TASK_ID, testStep);
    }

    @Test
    public void testGetStepsByFinishedFilter() throws Exception {
        Steps actual = stepsDao.getStepsByFinishedFilter(TASK_ID, true);
        assertNotNull(actual);
    }

    @Test(expected = TaskNotFoundException.class)
    public void testGetStepsByFinishedFilterIncorrectTaskId() throws Exception {
        Steps actual = stepsDao.getStepsByFinishedFilter(INCORRECT_TASK_ID, true);
    }
}