package ru.fsep.enterprise.fseper.service.exceptions;

import com.google.common.base.MoreObjects;

public class UserNotFoundException extends RuntimeException {
    private String message;

    public UserNotFoundException(int userId){
        this.message = "User with id <" + userId + "> is not found";
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("message", message)
                .toString();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
