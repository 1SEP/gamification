package ru.fsep.enterprise.fseper.service.exceptions;

import com.google.common.base.MoreObjects;

public class PostsNotFoundException extends RuntimeException {
    private String message;

    public PostsNotFoundException(int postId) {
        this.message = "Post with id <" + postId + "> was not found";
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("Message:", message).toString();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
