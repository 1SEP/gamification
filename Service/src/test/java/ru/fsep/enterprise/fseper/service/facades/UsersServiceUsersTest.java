package ru.fsep.enterprise.fseper.service.facades;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.fsep.enterprise.fseper.AppContext;
import ru.fsep.enterprise.fseper.AppTestContext;
import ru.fsep.enterprise.fseper.service.dao.UsersDao;
/**
 * Created by Ôëþð on 12.07.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestContext.class, AppContext.class})
public class UsersServiceUsersTest {
    @Autowired
    UsersServiceFacade usersServiceFacade;
    @Autowired
    UsersDao usersDao;
    @Mock
    UsersServiceFacadeImpl usersServiceFacadeImpl;
    @Test
    public void testGetUsers(){

    }
}
