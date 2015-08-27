package ru.fsep.enterprise.fseper.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.fsep.enterprise.fseper.models.Step;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.jdbc.utils.SqlQueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * Created by ramil on 09.07.2015.
 */
@Repository
public class TasksDaoImpl implements TasksDao {

    @Autowired
    private DaoArgumentsVerifier verifier;

    @Autowired
    private ParamsMapper paramsMapper;

    @Autowired
    private SqlQueryExecutor sqlQueryExecutor;

    @Autowired
    StepsDao stepsDao;

    public TasksDaoImpl() {
    }

    public TasksDaoImpl(SqlQueryExecutor sqlQueryExecutor, ParamsMapper paramsMapper, DaoArgumentsVerifier verifier) {
        this.sqlQueryExecutor = sqlQueryExecutor;
        this.paramsMapper = paramsMapper;
        this.verifier = verifier;
        stepsDao = new StepsDaoImpl(sqlQueryExecutor, paramsMapper, verifier);
    }

    public static final String SQL_GET_TASKS_BY_ID =
            "SELECT * FROM task WHERE (id = :userId)";


    public static final String SQL_GET_TASK_BY_ID =
            "SELECT * FROM task WHERE (id = :taskId)";

    public static final String SQL_GET_TASKS_BY_DATE =
            "SELECT * FROM task WHERE (due_data = :date)";

    public static final String SQL_INSERT_INTO_TASK =
            "INSERT INTO task VALUES (:privated, :description, :due_data, :steps_id, finished)";

    public static final String SQL_UPDATE_TASK =
            "UPDATE task VALUES (:id, :privated, :description, :due_data, :steps_id, :steps, :finished)";

    public static final String SQL_DELETE_FROM_TASK_BY_ID =
            "DELETE FROM tasks WHERE (task_id = :taskId)";

    public static final String SQL_GET_PRIVATED_TASKS =
            "SELECT * FROM tasks WHERE (users_id = :userId)";

    public static final String SQL_GET_FINISHED_TASKS =
            "SELECT * FROM tasks WHERE (users_id = :userId)";


    public static final RowMapper<Task> TASK_ROW_MAPPER = new RowMapper<Task>() {
        public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            boolean isPrivate = rs.getBoolean("privated");
            String description = rs.getString("description");
            String str_due_date = rs.getString("due_date");
            Date duedate = new Date(str_due_date);
            List<Step> steps = Collections.EMPTY_LIST;
            boolean finished = rs.getBoolean("finished");

            return new Task(id, isPrivate, description, duedate, steps, finished);
        }
    };

    public Task getTask(int taskId) {
        verifier.verifyTask(taskId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("taskId"), asList(taskId));
        Task task = sqlQueryExecutor.queryForObject(SQL_GET_TASK_BY_ID, paramMap, TASK_ROW_MAPPER);
        task.setSteps(stepsDao.getSteps(taskId));
        return task;
    }

    public Task updateTask(Task task) {
        verifier.verifyTask(task.getId());
        Map<String, Object> paramMap = paramsMapper.asMap(
                asList("taskId", "privated", "description", "due_data", "steps", "finished"),
                asList(task.getId(), task.isPrivated(), task.getDescription(), task.getDueDate(), task.getSteps(), task.isFinished()));

        sqlQueryExecutor.updateQuery(SQL_UPDATE_TASK, paramMap);

        List<Step> steps = stepsDao.getSteps(task.getId());
        task.setSteps(steps);

        return task;
    }

    public List<Task> getTasksByDate(int userId, Date date) {
        verifier.verifyUserById(userId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("userId", "due_data"), asList(userId, date));
        List<Task> tasks = sqlQueryExecutor.queryForObjects(SQL_GET_TASKS_BY_DATE, paramMap, TASK_ROW_MAPPER);
        for (Task task : tasks) {
            int taskId = task.getId();
            List<Step> steps = stepsDao.getSteps(taskId);
            task.setSteps(steps);
        }
        return tasks;
    }

    public void assignmentTask(Task task, int userId) {
        int taskId = task.getId();
        verifier.verifyAssignment(userId, taskId);
        Map<String, Object> paramMap = paramsMapper.asMap(
                asList("taskId", "privated", "description", "due_data", "steps", "finished"),
                asList(task.getId(), task.isPrivated(), task.getDescription(), task.getDueDate(), task.getSteps(), task.isFinished()));
        sqlQueryExecutor.queryForObject(SQL_INSERT_INTO_TASK, paramMap, TASK_ROW_MAPPER);
    }

    public void removeTask(int taskId) {
        verifier.verifyTask(taskId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("taskId"), asList(taskId));
        sqlQueryExecutor.updateQuery(SQL_DELETE_FROM_TASK_BY_ID, paramMap);
    }

    public List<Task> getTasks(int userId) {
        verifier.verifyUserById(userId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("userId"), asList(userId));
        List<Task> tasks = sqlQueryExecutor.queryForObjects(SQL_GET_TASKS_BY_ID, paramMap, TASK_ROW_MAPPER);

        for (Task task : tasks) {
            int taskId = task.getId();
            List<Step> steps = stepsDao.getSteps(taskId);
            task.setSteps(steps);
        }
        return tasks;
    }

    public List<Task> getPrivatedTasks(int userId) {
        verifier.verifyUserById(userId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("userId"), asList(userId));
        List<Task> tasks = sqlQueryExecutor.queryForObjects(SQL_GET_PRIVATED_TASKS, paramMap, TASK_ROW_MAPPER);

        for (Task task : tasks) {
            int taskId = task.getId();
            List<Step> steps = stepsDao.getSteps(taskId);
            task.setSteps(steps);
        }

        return tasks;
    }

    public List<Task> getFinishedTasks(int userId) {
        verifier.verifyUserById(userId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("userId"), asList(userId));
        List<Task> tasks = sqlQueryExecutor.queryForObjects(SQL_GET_FINISHED_TASKS, paramMap, TASK_ROW_MAPPER);

        for (Task task : tasks) {
            int taskId = task.getId();
            List<Step> steps = stepsDao.getSteps(taskId);
            task.setSteps(steps);
        }

        return tasks;
    }
}
