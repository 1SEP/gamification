package ru.fsep.enterprise.fseper.service.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.jdbc.utils.SqlQueryExecutor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * Created by ramil on 09.07.2015.

 */
public class TasksDaoImpl implements TasksDao {


    public TasksDaoImpl(DaoArgumentsVerifier verifier, ParamsMapper paramsMapper, SqlQueryExecutor sqlQueryExecutor) {
        this.verifier = verifier;
        this.paramsMapper = paramsMapper;
        this.sqlQueryExecutor = sqlQueryExecutor;
    }

    private DaoArgumentsVerifier verifier;


    private ParamsMapper paramsMapper;


    private SqlQueryExecutor sqlQueryExecutor;

    static final String SQL_GET_TASKS_BY_ID =
            "SELECT * FROM task WHERE (id = :taskId)";


    static final String SQL_GET_TASK_BY_ID =
            "SELECT user_id FROM task WHERE (id = :taskId)";

    static final String SQL_GET_TASK_BY_DATE =
            "SELECT * FROM task WHERE (due_data = :date)";

    static final String SQL_INSERT_INTO_TASK =
            "INSERT INTO task VALUES (:privated, :description, :due_data, :steps_id, finished)";

    static final String SQL_UPDATE_TASK =
            "INSERT UPDATE task VALUES (:id, :privated, :description, :due_data, :steps_id,:steps, :finished)";

    static final String SQL_DELETE_FROM_TASK_BY_IDS =
            "DELETE FROM tasks WHERE (user_id = :userId)";

    static final String SQL_GET_PRIVATED =
            "SELECT privated FROM task WHERE (id = :userId)";

    static final String SQL_GET_FINISHED =
            "SELECT finished FROM task WHERE (id = :taskId)";



    public static final RowMapper<Task> USER_ROW_MAPPER = new BeanPropertyRowMapper<Task>(Task.class);


    public Task getTask(int taskId) {
        verifier.verifyTask(taskId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("taskId"), asList(taskId));
        Task task = sqlQueryExecutor.queryForObject(SQL_GET_TASK_BY_ID, paramMap, USER_ROW_MAPPER);
        return task;
    }

    public Task updateTask(Task task) {
//        verifier.verifyTask(task.getId());
//        Map<String, Object> paramMap = paramsMapper.asMap(asList("taskId, privated", "description", "due_data", "steps_id",
//                "steps", "finished"), asList(task.getId(), task.isPrivated(), task.getDescription(),
//                task.getDueDate(), task.getSteps_id(), task.getSteps(), task.isFinished()));
//
//        sqlQueryExecutor.updateQuery(SQL_UPDATE_TASK, paramMap);
        return task;
    }

    public List<Task> getTasksByDate(int taskId, Date date) {
        verifier.verifyTask(taskId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("taskId", "due_data"), asList(taskId, date));
        List<Task> task =  sqlQueryExecutor.queryForObjects(SQL_GET_TASK_BY_DATE, paramMap, USER_ROW_MAPPER);
        return task;
    }

    public void assignmentTask(Task task, int userId) {
//        verifier.verifyTask(task.getId());
//        Map<String, Object> paramMap = paramsMapper.asMap(asList("taskId, privated", "description", "due_data", "steps_id",
//                "steps", "finished"), asList(task.getId(), task.isPrivated(), task.getDescription(),
//                task.getDueDate(), task.getSteps_id(), task.getSteps(), task.isFinished()));
//        sqlQueryExecutor.queryForObject(SQL_INSERT_INTO_TASK, paramMap, USER_ROW_MAPPER);
    }

    public void removeTask(int taskId) {
        verifier.verifyTask(taskId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("taskId"), asList(taskId));
        sqlQueryExecutor.updateQuery(SQL_DELETE_FROM_TASK_BY_IDS, paramMap);
    }

    public List<Task> getTasks(int userId) {
        return null;
    }

    public List<Task> getPrivatedTasks(int taskId) {
        verifier.verifyTask(taskId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("taskId"), asList(taskId));
        List<Task> task =  sqlQueryExecutor.queryForObjects(SQL_GET_PRIVATED, paramMap , USER_ROW_MAPPER);
        return task;
    }

    public List<Task> getFinishedTasks(int taskId) {
        verifier.verifyTask(taskId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("taskId"), asList(taskId));
        List<Task> task =  sqlQueryExecutor.queryForObjects(SQL_GET_FINISHED, paramMap , USER_ROW_MAPPER);
        return task;
    }
}
