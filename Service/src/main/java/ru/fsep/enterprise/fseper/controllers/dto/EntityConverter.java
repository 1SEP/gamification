package ru.fsep.enterprise.fseper.controllers.dto;

import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.models.User;

import java.util.List;

/**
 * Created by Ôëþð on 15.07.2015.
 */
public interface EntityConverter {
    TaskDto fromTask(Task entity);
    TasksDto fromTasks(List<Task> entities);
    UserDto fromUser(User entity);
    UsersDto fromUsers(List<User> entities);
}
