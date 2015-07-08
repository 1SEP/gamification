package ru.fsep.enterprise.fseper.service.serviceFacades;

import ru.fsep.enterprise.fseper.models.Task;

import java.util.Date;
import java.util.List;

/**
 * Created by Ôëşğ on 07.07.2015.
 */
public interface TasksServiceFacade {
    void assignmentTask(Task task);
    Task getTask(int taskId);
    void updateTask(int taskId);
    void removeTask(int taskId);
    List<Task> getPrivatedTaskList();
    List<Task> getFinishedTaskList();
    List<Task> getTaskListByDate(Date date);
}
