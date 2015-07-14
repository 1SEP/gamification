package ru.fsep.enterprise.fseper.service.jdbc.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rauf on 09.07.2015.
 */
public class ParamsMapperJDBCImpl implements ParamsMapper {
    public Map<String, Object> asMap(List<String> keys, List<?> values) {
        if (keys.size() != values.size()) {
            throw new IllegalArgumentException();
        } else {
            Map<String, Object> result = new HashMap<String, Object>();
            for (int i = 0; i < keys.size(); i++) {
                result.put(keys.get(i), values.get(i));
            }
            return result;
        }
    }
}
