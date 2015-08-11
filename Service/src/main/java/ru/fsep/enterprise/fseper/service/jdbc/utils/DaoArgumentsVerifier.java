package ru.fsep.enterprise.fseper.service.jdbc.utils;

public interface DaoArgumentsVerifier {
    void verifyUser(int userId);
    void verifyTask(int taskId);
    void verifyAssignment(int userId, int taskId);
}
