package ru.fsep.enterprise.fseper.service.serviceFacades;

import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.service.dao.TasksDao;

import java.util.Date;
import java.util.List;

/**
 * Created by Ôëþð on 07.07.2015.
 */
public class TasksServiceFacadeImpl implements TasksServiceFacade {
    private TasksDao tasksDao;

    public TasksServiceFacadeImpl(TasksDao tasksDao) {
        this.tasksDao = tasksDao;
    }

    public void assignmentTask(Task task) {
        tasksDao.assignmentTask(task);
    }

    public Task getTask(int taskId) {
        return tasksDao.getTask(taskId);
    }

    public void updateTask(int taskId) {
        tasksDao.updateTask(taskId);
    }

    public void removeTask(int taskId) {
        tasksDao.removeTask(taskId);
    }

    public List<Task> getPrivatedTaskList() {
        return tasksDao.getPrivatedTaskList();
    }

    public List<Task> getFinishedTaskList() {
        return tasksDao.getFinishedTaskList();
    }

    public List<Task> getTaskListByDate(Date date) {
        return null;
    }
}
