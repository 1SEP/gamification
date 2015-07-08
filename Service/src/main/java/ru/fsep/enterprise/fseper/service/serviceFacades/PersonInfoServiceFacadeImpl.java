package ru.fsep.enterprise.fseper.service.serviceFacades;

import ru.fsep.enterprise.fseper.models.PersonInfo;
import ru.fsep.enterprise.fseper.service.dao.PersonInfoDao;

/**
 * Created by Ôëþð on 08.07.2015.
 */
public class PersonInfoServiceFacadeImpl implements PersonInfoServiceFacade {
    PersonInfoDao personInfoDao;
    public void signUp(PersonInfo personInfo) {
        personInfoDao.signUp(personInfo);
    }
}
