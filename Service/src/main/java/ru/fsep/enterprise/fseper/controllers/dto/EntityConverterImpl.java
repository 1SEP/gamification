package ru.fsep.enterprise.fseper.controllers.dto;

import org.springframework.stereotype.Component;
import ru.fsep.enterprise.fseper.models.Task;
import ru.fsep.enterprise.fseper.models.User;

import java.util.List;

/**
 * Created by Ôëþð on 15.07.2015.
 */

@Component
public class EntityConverterImpl implements EntityConverter {

    private final String INT_TO_STR_USER_ADAPTER_ID = "IntegerToString";
    private final String DOUBLE_TO_STR_USER_ADAPTER_RATING = "DoubleToString";
    private final String URL_TO_STR_USER_ADAPTER_NAME = "DoubleToString";

    public TaskDto fromTask(Task entity) {
        return null;
    }

    public TasksDto fromTasks(List<Task> entities) {
        return null;
    }

    public UserDto fromUser(User entity) {

        return null;
    }

    public UsersDto fromUsers(List<User> entities) {
        return null;
    }
}
