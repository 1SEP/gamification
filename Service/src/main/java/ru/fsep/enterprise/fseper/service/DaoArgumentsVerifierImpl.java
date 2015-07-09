package ru.fsep.enterprise.fseper.service;

public class DaoArgumentsVerifierImpl implements DaoArgumentsVerifier {

    //language=SQL
    private static final String SQL_COUNT_TASKS_BY_ID =
            "SELECT COUNT (*) FROM tasks WHERE (id = :taskId)";

    //language=SQL
    private static final String SQL_COUNT_USERS_BY_ID =
            "SELECT COUNT (*) FROM USERS WHERE (ID = :userId)";

    public void verifyUser(int userId) {
        
    }

    public void verifyTask(int taskId) {

    }
}
