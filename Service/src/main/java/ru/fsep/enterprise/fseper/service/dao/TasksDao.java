package ru.fsep.enterprise.fseper.service.dao;

import ru.fsep.enterprise.fseper.models.Task;

import java.util.List;

/**
 * Created by Ôëşğ on 07.07.2015.
 */
public interface TasksDao {
    void assignmentTask(Task task, int userId);
    Task getTask(int taskId);
    void updateTask(int taskId);
    void removeTask(int taskId);
    List<Task> getPrivatedTasks();
    List<Task> getFinishedTasks();
    List<Task> getTasksByDate();
}
