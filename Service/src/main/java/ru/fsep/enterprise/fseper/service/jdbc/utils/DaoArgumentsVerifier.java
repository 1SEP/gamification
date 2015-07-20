package ru.fsep.enterprise.fseper.service.jdbc.utils;

import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.User;

public interface DaoArgumentsVerifier {
    void verifyUser(User user);
    void verifyUserById(int userId);
    void verifyUserByName(String firstName, String lastName);
    void verifyTask(int taskId);
    void verifyPostById(int postId);
    void verifyPost(Post post);
}
