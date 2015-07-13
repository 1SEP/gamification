package ru.fsep.enterprise.fseper.service.dao.service.jdbc.utils;

public interface DaoArgumentsVerifier {
    void verifyUser(int userId);
    void verifyTask(int taskId);
}
