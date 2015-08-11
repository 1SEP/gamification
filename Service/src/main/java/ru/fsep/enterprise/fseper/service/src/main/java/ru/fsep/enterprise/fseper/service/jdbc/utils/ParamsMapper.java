package ru.fsep.enterprise.fseper.service.jdbc.utils;

import java.util.List;
import java.util.Map;

/**
 * Created by Rauf on 09.07.2015.
 */
public interface ParamsMapper {
    Map<String, Object> asMap(List<String> keys, List<?> values);
}
