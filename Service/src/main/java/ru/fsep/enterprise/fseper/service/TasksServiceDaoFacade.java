package ru.fsep.enterprise.fseper.service;

import ru.fsep.enterprise.fseper.models.Task;

import java.util.List;

/**
 * Created by Ôëşğ on 07.07.2015.
 */
public interface TasksServiceDaoFacade {
    void assignmentTask(Task task);
    Task getTask(int taskId);
    void updateTask(int taskId);
    void removeTask(int taskId);
    List<Task> getPrivatedTaskList();
    List<Task> getFinishedTaskList();
    List<Task> getTaskListByDate();
}
