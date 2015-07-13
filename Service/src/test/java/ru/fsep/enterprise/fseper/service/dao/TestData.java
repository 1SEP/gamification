package ru.fsep.enterprise.fseper.service.dao;

import com.google.common.collect.ImmutableMap;
import ru.fsep.enterprise.fseper.service.dao.models.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marsel Sidikov and Ildar Almakayev (First Software Engineering Platform))
 */
public class TestData {
    public static final int USER_ID = 1;
    public static final User USER = new User(USER_ID, null, null, null);
    public static final Map<String, Object> USER_MAP = createMap();

    private static Map<String, Object> createMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", USER_ID);
        return map;
    }
}
