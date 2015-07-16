package ru.fsep.enterprise.fseper.service.exceptions;

import com.google.common.base.MoreObjects;

public class UserNotFoundException extends RuntimeException {
    private String message;

    public UserNotFoundException(int userId) {
        this.message = "User with id <" + userId + "> was not found";
    }

    public UserNotFoundException(String firstName, String lastName){
        this.message = "User(s) with this name <" + firstName + "> and surname <" + lastName + "> was(were) not found";
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Message:", message)
                .toString();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
