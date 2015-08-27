package ru.fsep.enterprise.fseper.service.dao;

import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.models.Tasks;

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

    Tasks getTasks(int userId);

    Tasks getTasksByPrivatedFilter(int userId, boolean privated);

    Tasks getTasksByFinishedFilter(int userId, boolean finished);

    Tasks getTasksByDate(int usersId, Date date);
}
