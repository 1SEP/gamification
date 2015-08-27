package ru.fsep.enterprise.fseper.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.fsep.enterprise.fseper.models.Steps;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.models.Tasks;
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

    public static final String SQL_GET_ALL_TASKS_BY_USER_ID =
            "SELECT * FROM task, tasks WHERE (tasks.user_id = :userId)";

    public static final String SQL_GET_TASK_BY_ID =
            "SELECT * FROM task WHERE (id = :taskId)";

    public static final String SQL_GET_TASKS_BY_DATE =
            "SELECT * FROM task, tasks WHERE (tasks.user_id = :userId AND task.due_data = :dueDate)";

    public static final String SQL_INSERT_INTO_TASK =
            "INSERT INTO task VALUES (:privated, :description, :due_data, finished)";

    public static final String SQL_UPDATE_TASK =
            "UPDATE task VALUES (:privated, :description, :due_data, :finished)";

    public static final String SQL_DELETE_FROM_TASK_BY_ID =
            "DELETE FROM task WHERE (id = :taskId)";

    public static final String SQL_GET_PRIVATED_TASKS =
            "SELECT * FROM task, tasks WHERE (tasks.users_id = :userId AND task.privated = :privated)";

    public static final String SQL_GET_FINISHED_TASKS =
            "SELECT * FROM task, tasks WHERE (tasks.users_id = :userId AND task.finished = :finished)";

    public static final RowMapper<Task> TASK_ROW_MAPPER = new RowMapper<Task>() {
        public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            boolean isPrivate = rs.getBoolean("privated");
            String description = rs.getString("description");
            String str_due_date = rs.getString("due_date");
            Date duedate = new Date(str_due_date);
            Steps steps = new Steps(Collections.EMPTY_LIST);
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

        Steps steps = stepsDao.getSteps(task.getId());
        task.setSteps(steps);
        return task;
    }

    public Tasks getTasksByDate(int userId, Date date) {
        verifier.verifyUserById(userId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("userId", "dueDate"), asList(userId, date));
        List<Task> tasksList = sqlQueryExecutor.queryForObjects(SQL_GET_TASKS_BY_DATE, paramMap, TASK_ROW_MAPPER);

        for (Task task : tasksList) {
            int taskId = task.getId();
            Steps steps = stepsDao.getSteps(taskId);
            task.setSteps(steps);
        }

        Tasks tasks = new Tasks(tasksList);
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

    public Tasks getTasks(int userId) {
        verifier.verifyUserById(userId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("userId"), asList(userId));
        List<Task> taskList = sqlQueryExecutor.queryForObjects(SQL_GET_ALL_TASKS_BY_USER_ID, paramMap, TASK_ROW_MAPPER);

        for (Task task : taskList) {
            int taskId = task.getId();
            Steps steps = stepsDao.getSteps(taskId);
            task.setSteps(steps);
        }
        Tasks tasks = new Tasks(taskList);
        return tasks;
    }

    public Tasks getTasksByPrivatedFilter(int userId, boolean privated) {
        verifier.verifyUserById(userId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("userId"), asList(userId));
        List<Task> tasksList = sqlQueryExecutor.queryForObjects(SQL_GET_PRIVATED_TASKS, paramMap, TASK_ROW_MAPPER);

        for (Task task : tasksList) {
            int taskId = task.getId();
            Steps steps = stepsDao.getSteps(taskId);
            task.setSteps(steps);
        }
        Tasks tasks = new Tasks(tasksList);
        return tasks;
    }

    public Tasks getTasksByFinishedFilter(int userId, boolean finished) {
        verifier.verifyUserById(userId);
        Map<String, Object> paramMap = paramsMapper.asMap(asList("userId"), asList(userId));
        List<Task> tasksList = sqlQueryExecutor.queryForObjects(SQL_GET_FINISHED_TASKS, paramMap, TASK_ROW_MAPPER);

        for (Task task : tasksList) {
            int taskId = task.getId();
            Steps steps = stepsDao.getSteps(taskId);
            task.setSteps(steps);
        }
        Tasks tasks = new Tasks(tasksList);
        return tasks;
    }
}
