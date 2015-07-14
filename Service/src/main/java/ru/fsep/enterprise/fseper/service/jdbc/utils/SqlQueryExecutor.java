package ru.fsep.enterprise.fseper.service.jdbc.utils;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Ôëþð on 09.07.2015.
 */
public interface SqlQueryExecutor {
    <T> List<T> queryForObjects(String sql, RowMapper<T> rowMapper);
    <T> List<T> queryForObjects(String sql, Map<String, Object> paramMap, RowMapper<T> rowMapper);
    <T> T queryForObject(String sql, Map<String, Object> paramMap, RowMapper<T> rowMapper);
    void updateQuery(String sql, Map<String, Object> paramMap);
    int queryForInt(String sql, Map<String, Object> paramMap);
    int queryForInt(String sql);
}

