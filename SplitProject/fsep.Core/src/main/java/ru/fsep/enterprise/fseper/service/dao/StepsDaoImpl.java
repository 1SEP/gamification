package ru.fsep.enterprise.fseper.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.fsep.enterprise.fseper.models.Step;
import ru.fsep.enterprise.fseper.service.jdbc.utils.DaoArgumentsVerifier;
import ru.fsep.enterprise.fseper.service.jdbc.utils.ParamsMapper;
import ru.fsep.enterprise.fseper.service.jdbc.utils.SqlQueryExecutor;

import org.springframework.jdbc.core.RowMapper;
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

    public static final RowMapper<Step> STEPS_ROW_MAPPER= new RowMapper<Step>() {
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

    public List<Step> getSteps(int taskId) {
        daoArgumentsVerifier.verifyTask(taskId);
        Map<String, Object> stepsParamMap = paramsMapper.asMap(asList("taskId"), asList(taskId));
        List<Step> steps = sqlQueryExecutor.queryForObjects(SQL_GET_STEPS_BY_TASKID, stepsParamMap, STEPS_ROW_MAPPER);
        return steps;
    }
}
