package ru.fsep.enterprise.fseper.Exception;

/**
 * Created by ramil on 20.07.2015.
 */
public class TaskNotFoundException extends RuntimeException {
    private String message = null;
    public TaskNotFoundException(int taskId) { this.message = "Task with id < " + taskId + "> not found";}

    @Override
    public String toString() { return message;}

    @Override
    public String getMessage() { return message; }


}
