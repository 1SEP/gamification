package ru.fsep.enterprise.fseper.controllers.dto;

import ru.fsep.enterprise.fseper.models.Task;

import java.util.List;

/**
 * Created by ���� on 15.07.2015.
 */
public interface EntityConverter {
    TaskDto fromTask(Task entity);
    TasksDto fromTasks(List<Task> entities);
}
