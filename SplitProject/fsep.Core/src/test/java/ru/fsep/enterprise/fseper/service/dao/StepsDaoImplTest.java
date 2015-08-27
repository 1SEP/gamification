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
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static ru.fsep.enterprise.fseper.service.dao.StepsDaoImpl.SQL_GET_STEP;
import static ru.fsep.enterprise.fseper.service.dao.StepsDaoImpl.STEPS_ROW_MAPPER;
import static ru.fsep.enterprise.fseper.test.data.TestDataForTaskDao.INCORRECT_TASK_ID;
import static ru.fsep.enterprise.fseper.test.data.TestDataForTaskDao.TASK_ID;

public class StepsDaoImplTest {

    StepsDao stepsDao;
    @Mock
    SqlQueryExecutor sqlQueryExecutor;
    @Mock
    ParamsMapper paramsMapper;
    @Mock
    DaoArgumentsVerifier argumentsVerifier;

    private void stubbingForMocks() {
        doThrow(TaskNotFoundException.class).when(argumentsVerifier).verifyTask(INCORRECT_TASK_ID);

        List<Step> testStepsList = new ArrayList<Step>();
        Step testStep = new Step(1, TASK_ID, "test_description", true);
        testStepsList.add(testStep);
        Map<String, Object> testParamMap = paramsMapper.asMap(asList("taskId"), asList(TASK_ID));

        doReturn(testStepsList).when(sqlQueryExecutor).queryForObjects(StepsDaoImpl.SQL_GET_STEPS_BY_TASKID, testParamMap, STEPS_ROW_MAPPER);
        doReturn(testStep).when(sqlQueryExecutor).queryForObject(SQL_GET_STEP, testParamMap, STEPS_ROW_MAPPER);
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
        verify(argumentsVerifier).verifyTask(TASK_ID);
        assertNotNull("The reference of steps list is NULL!", steps);
    }

    @Test(expected = TaskNotFoundException.class)
    public void testGetStepsIncorrectTaskId() throws Exception {
        stepsDao.getSteps(INCORRECT_TASK_ID);
        verify(argumentsVerifier).verifyTask(INCORRECT_TASK_ID);
    }

    @Test
    public void testGetStep() throws Exception {
        Step step = stepsDao.getStep(TASK_ID, 1);
        
    }

    @Test
    public void testAdd() throws Exception {
        stepsDao.addStep(TASK_ID, null);
    }

    @Test
    public  void testRemove() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testgetStepsByFinishedFilter() throws Exception {

    }
}