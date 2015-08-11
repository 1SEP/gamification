package ru.fsep.enterprise.fseper.service.jdbc.utils;

import ru.fsep.enterprise.fseper.Exception.TaskNotAssignedToUserException;
import ru.fsep.enterprise.fseper.Exception.TaskNotFoundException;

import java.util.Map;

import static java.util.Arrays.asList;

public class DaoArgumentsVerifierImpl implements DaoArgumentsVerifier {

    private SqlQueryExecutor sqlQueryExecutor;
    private ParamsMapper paramsMapper;

    //language=SQL
    static final String SQL_GET_TASKS_BY_ID =
            "SELECT * FROM tasks WHERE (id = :taskId)";

    //language=SQL
    private static final String SQL_COUNT_OF_ASSIGNMENTS_BY_IDS =
            "SELECT COUNT (*) FROM task WHERE (user_id = :userId AND task_id = :taskId)";

    //language=SQL
    public void verifyUser(int userId) {

    }

    public void verifyTask(int taskId) {
        Map<String , Object> paramMap = paramsMapper.asMap(asList("taskId") , asList(taskId));
        int taskCount = sqlQueryExecutor.queryForInt(SQL_GET_TASKS_BY_ID, paramMap);
        if(taskCount !=1 )
            throw new TaskNotFoundException(taskId);
    }

    @Override
    public void verifyAssignment(int userId, int taskId) {
        verifyUser(userId);
        verifyTask(taskId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("userId", "taskId"), asList(userId, taskId));
        int assignmentsCount = sqlQueryExecutor.queryForInt(SQL_COUNT_OF_ASSIGNMENTS_BY_IDS, paramMap);
        if (assignmentsCount != 1) {
            throw new TaskNotAssignedToUserException(userId, taskId);
        }
    }
}
