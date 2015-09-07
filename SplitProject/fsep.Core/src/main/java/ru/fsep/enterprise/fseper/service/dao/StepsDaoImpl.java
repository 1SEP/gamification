package ru.fsep.enterprise.fseper.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.fsep.enterprise.fseper.models.Step;
import ru.fsep.enterprise.fseper.models.Steps;
import ru.fsep.enterprise.fseper.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.jdbc.utils.SqlQueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

@Repository
public class StepsDaoImpl implements StepsDao {

    @Autowired
    private SqlQueryExecutor sqlQueryExecutor;
    @Autowired
    private DaoArgumentsVerifier daoArgumentsVerifier;
    @Autowired
    private ParamsMapper paramsMapper;

    public static final RowMapper<Step> STEPS_ROW_MAPPER = new RowMapper<Step>() {
        public Step mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            int taskId = rs.getInt("tasks_id");
            String description = rs.getString("description");
            boolean finished = rs.getBoolean("finished");
            return new Step(id, taskId, description, finished);
        }
    };

    public StepsDaoImpl() {
    }

    public StepsDaoImpl(SqlQueryExecutor sqlQueryExecutor, ParamsMapper paramsMapper, DaoArgumentsVerifier daoArgumentsVerifier) {
        this.sqlQueryExecutor = sqlQueryExecutor;
        this.paramsMapper = paramsMapper;
        this.daoArgumentsVerifier = daoArgumentsVerifier;
    }

    //language=SQL
    public static final String SQL_GET_STEPS_BY_TASKID = "SELECT * FROM step WHERE tasks_id = :taskId;";
    //language=SQL
    public static final String SQL_GET_STEP = "SELECT * FROM step WHERE id = :stepId AND tasks_id = :taskId;";
    //language=SQL
    public static final String SQL_INSERT_STEP_BY_TASK_ID = "INSERT INTO step VALUES (:taskId, :description, :finished);";
    //language=SQL
    public static final String SQL_DELETE_STEP = "DELETE FROM step WHERE (id = :stepId AND task_id = :taskId);";
    //language=SQL
    public static final String SQL_GET_ALL_FINISHED_STEPS = "SELECT * FROM step WHERE " +
            "(task_id = :taskId AND finished = :finished);";
    //language=SQL
    public static final String SQL_UPDATE_STEP = "UPDATE step SET description = :description, finished = :finished, " +
            "task_id = :task_id WHERE id = :stepId;";

    public Steps getSteps(int taskId) {
        Map<String, Object> stepsParamMap = paramsMapper.asMap(asList("taskId"), asList(taskId));
        List<Step> stepsList = sqlQueryExecutor.queryForObjects(SQL_GET_STEPS_BY_TASKID, stepsParamMap, STEPS_ROW_MAPPER);
        Steps steps = new Steps(stepsList);
        return steps;
    }

    public Step getStep(int taskId, int stepId) {
        daoArgumentsVerifier.verifyTask(taskId);
        Map<String, Object> stepParamMap = paramsMapper.asMap(asList("taskId", "stepId"), asList(taskId, stepId));
        Step step = sqlQueryExecutor.queryForObject(SQL_GET_STEP, stepParamMap, STEPS_ROW_MAPPER);
        return step;
    }

    public Steps getStepsByFinishedFilter(int taskId, boolean finished) {
        daoArgumentsVerifier.verifyTask(taskId);
        Map<String, Object> stepsParamMap = paramsMapper.asMap(asList("taskId", "finished"), asList(taskId, finished));
        List<Step> stepList = sqlQueryExecutor.queryForObjects(SQL_GET_ALL_FINISHED_STEPS, stepsParamMap, STEPS_ROW_MAPPER);
        Steps steps = new Steps(stepList);
        return steps;
    }

    public void addStep(int taskId, Step step) {
        daoArgumentsVerifier.verifyTask(taskId);
        Map<String, Object> stepsParamMap = paramsMapper.asMap(asList("taskId", "description", "finished"),
                asList(taskId, step.getDescription(), step.isFinished()));
        sqlQueryExecutor.updateQuery(SQL_INSERT_STEP_BY_TASK_ID, stepsParamMap);
    }

    public Step updateStep(int taskId, Step step) {
        daoArgumentsVerifier.verifyTask(taskId);
        Map<String, Object> stepParamMap = paramsMapper.asMap(asList("taskId", "stepId", "description", "finished"),
                asList(taskId, step.getId(), step.getDescription(), step.isFinished()));
        sqlQueryExecutor.updateQuery(SQL_UPDATE_STEP, stepParamMap);
        return step;
    }

    public void removeStep(int taskId, int stepId) {
        daoArgumentsVerifier.verifyTask(taskId);
        Map<String, Object> stepsParamMap = paramsMapper.asMap(asList("taskId", "stepId"), asList(taskId, stepId));
        sqlQueryExecutor.updateQuery(SQL_DELETE_STEP, stepsParamMap);
    }
}
