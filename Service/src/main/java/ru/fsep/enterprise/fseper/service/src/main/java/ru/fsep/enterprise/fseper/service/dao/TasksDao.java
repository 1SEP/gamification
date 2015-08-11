package ru.fsep.enterprise.fseper.service.dao;

import ru.fsep.enterprise.fseper.models.Task;

import java.util.Date;
import java.util.List;

/**
 * Created by Ôëşğ on 07.07.2015.
 */
public interface TasksDao {

    void assignmentTask(Task task, int taskId);
    Task getTask(int taskId);
    Task updateTask(Task task);
    void removeTask(int taskId);
    List<Task> getTasks(int userId);
    List<Task> getPrivatedTasks(int taskId);
    List<Task> getFinishedTasks(int taskId);
    List<Task> getTasksByDate(int userId, Date date);
}
