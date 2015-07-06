package ru.fsep.enterprise.fseper.service;

import ru.fsep.enterprise.fseper.models.User;

/**
 * Created by Ôëþð on 06.07.2015.
 */
public class UsersServiceFacadeImpl implements UsersServiceFacade {
    UsersDAO usersDAO;

    public UsersServiceFacadeImpl(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public void addUser() {
        usersDAO.addUser();
    }
}
