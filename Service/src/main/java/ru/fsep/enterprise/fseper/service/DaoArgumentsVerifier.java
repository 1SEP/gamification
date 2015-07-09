package ru.fsep.enterprise.fseper.service;

public interface DaoArgumentsVerifier {
    void verifyUser(int userId);
    void verifyTask(int taskId);
}
