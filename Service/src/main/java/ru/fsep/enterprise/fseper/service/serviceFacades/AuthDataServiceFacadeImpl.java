package ru.fsep.enterprise.fseper.service.serviceFacades;

import ru.fsep.enterprise.fseper.models.AuthData;
import ru.fsep.enterprise.fseper.service.dao.AuthDataDao;

/**
 * Created by Ôëþð on 08.07.2015.
 */
public class AuthDataServiceFacadeImpl implements AuthDataServiceFacade {
    AuthDataDao authDataDao;
    public void login(AuthData data) {
        authDataDao.logIn(data);
    }
}
