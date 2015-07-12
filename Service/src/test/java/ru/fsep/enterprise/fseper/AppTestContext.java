package ru.fsep.enterprise.fseper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.fsep.enterprise.fseper.service.dao.UsersDaoImpl;

/**
 * Created by Ôëþð on 12.07.2015.
 */
@Configuration
public class AppTestContext {
    @Bean
    public UsersDaoImpl usersDao(){
        return new UsersDaoImpl();
    }
}
