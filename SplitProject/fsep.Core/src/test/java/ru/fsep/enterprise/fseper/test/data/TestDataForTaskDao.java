package ru.fsep.enterprise.fseper.test.data;

import ru.fsep.enterprise.fseper.models.Task;

import java.util.*;

/**
 * Created by ramil on 13.07.2015.
 */
public class TestDataForTaskDao {
    public static final int TASK_ID = 1;
    public static final int USER_ID = 1;
    public static final int INCORRECT_TASK_ID = -5;
    public static final int INCORRECT_USER_ID = -5;
    public static final boolean PRIVATED = true;
    public static final boolean FINISHED = true;
    public static final List<Task> PRIVATED_TASK = getListPrivatedTasks();
    public static final List<Task> FINISHED_TASK = getListFinishedTasks();
    public static final Date DATE_TASK = test();
    public static final List<Task> TASK_LIST = getListOfTasks();
    public static final Task TASK = new Task(TASK_ID, PRIVATED, null, null, null, FINISHED);
    public static final Task INCORRECT_TASK = new Task(INCORRECT_TASK_ID, PRIVATED, null, null, null, FINISHED);

    public static final Map<String, Object> TASK_MAP = createMap();

    public static Date test() {
        Date data = new Date();
        return data;
    }


    private static Map<String, Object> createMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taskId", TASK_ID);
        map.put("protected", true);
        map.put("description", "test_descriptions");
        return map;
    }


    private static List<Task> getListOfTasks() {
        List<Task> result = new ArrayList<Task>();
        result.add(TASK);
        return result;
    }

    private static List<Task> getListPrivatedTasks() {
        List<Task> result = new ArrayList<Task>();
        result.add(TASK);
        return result;
    }

    private static List<Task> getListFinishedTasks() {
        List<Task> result = new ArrayList<Task>();
        result.add(TASK);
        return result;
    }
}
