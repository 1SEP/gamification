package ru.fsep.enterprise.fseper.service.exceptions;

import com.google.common.base.MoreObjects;

/**
 * Created by Ôëþð on 30.07.2015.
 */
public class TasksNotFoundException extends RuntimeException{
    public String message;

    public TasksNotFoundException(int taskId) {
        this.message = "Task with id <" + taskId + "> was not found";
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("message", message)
                .toString();
    }
}
