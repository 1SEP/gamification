package ru.fsep.enterprise.fseper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacadeImpl;

/**
 * Created by Ôëþð on 11.07.2015.
 */
@Configuration
@ComponentScan(basePackages = "ru.fsep.enterprise.fseper")
public class AppContext {
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//        String userName = " ";
//        String password = " ";
//        String dbUrl = " ";
//        String driverClassName = " ";
//        dataSource.setUsername(userName);
//        dataSource.setPassword(password);
//        dataSource.setUrl(dbUrl);
//        dataSource.setDriverClassName(driverClassName);
//
//        return  dataSource;
//    }
    @Bean
    public UsersServiceFacade usersServiceFacade(){
        return new UsersServiceFacadeImpl();
    }
}
