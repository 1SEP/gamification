package ru.fsep.enterprise.fseper.Exception;

/**
 * Created by ramil on 20.07.2015.
 */
public class TaskNotAssignedToUserException extends RuntimeException {
    private String message = null;
    public TaskNotAssignedToUserException(int taskId, int userId) { this.message = "Task <" + taskId + "> not assigned to user <" + userId + ">.";}

    @Override
    public String toString() { return message;}

    @Override
    public String getMessage() { return message; }

}
