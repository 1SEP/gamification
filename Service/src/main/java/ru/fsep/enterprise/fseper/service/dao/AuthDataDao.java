package ru.fsep.enterprise.fseper.service.dao;

import ru.fsep.enterprise.fseper.models.AuthData;

/**
 * Created by ���� on 08.07.2015.
 */
public interface AuthDataDao {
    void logIn(AuthData data);
}
