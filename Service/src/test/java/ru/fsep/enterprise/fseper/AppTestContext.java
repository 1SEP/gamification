package ru.fsep.enterprise.fseper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.fsep.enterprise.fseper.service.dao.UsersDaoImpl;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacadeImpl;

/**
 * Created by ���� on 12.07.2015.
 */
@Configuration
public class AppTestContext {
    @Bean
    public UsersServiceFacade usersServiceFacade(){
        return new UsersServiceFacadeImpl();
    }
}
