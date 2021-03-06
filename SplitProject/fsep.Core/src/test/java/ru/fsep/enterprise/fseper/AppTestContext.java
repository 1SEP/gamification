package ru.fsep.enterprise.fseper;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacadeImpl;

/**
 * Created by ���� on 12.07.2015.
 */
@Configuration
public class AppTestContext {
    @Bean
    public UsersServiceFacade usersServiceFacade(){
        return Mockito.mock(UsersServiceFacade.class);
    }
}
