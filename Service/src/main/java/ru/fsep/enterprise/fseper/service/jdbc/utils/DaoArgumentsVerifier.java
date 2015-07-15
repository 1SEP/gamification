package ru.fsep.enterprise.fseper.service.jdbc.utils;

public interface DaoArgumentsVerifier {
    void verifyUser(int userId);
    void verifyFirstNameAndLastName(String firstName, String lastName);
    void verifyTask(int taskId);
    void verifyPost(int postId);
}
