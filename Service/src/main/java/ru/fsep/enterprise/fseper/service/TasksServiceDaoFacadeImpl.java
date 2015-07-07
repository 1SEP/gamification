package ru.fsep.enterprise.fseper.service;

import ru.fsep.enterprise.fseper.models.Task;

import java.util.List;

/**
 * Created by Ôëþð on 07.07.2015.
 */
public class TasksServiceDaoFacadeImpl implements TasksServiceDaoFacade {
    private TasksDao tasksDao;

    public TasksServiceDaoFacadeImpl(TasksDao tasksDao) {
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

    public List<Task> getTaskListByDate() {
        return tasksDao.getTaskListByDate();
    }
}
