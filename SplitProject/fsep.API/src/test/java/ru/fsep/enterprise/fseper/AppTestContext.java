package ru.fsep.enterprise.fseper;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import ru.fsep.enterprise.fseper.service.facades.TasksServiceFacade;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacadeImpl;

import javax.sql.DataSource;

/**
 * Created by Ôëþð on 12.07.2015.
 */
@Configuration
public class AppTestContext {
    @Bean
    public UsersServiceFacade usersServiceFacade(){
        return Mockito.mock(UsersServiceFacade.class);
    }

    @Bean
    public TasksServiceFacade tasksServiceFacade(){ return Mockito.mock(TasksServiceFacade.class); }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String userName = "lqcbqfvsdadqgx";
        String password = "3sRCuOUA5BykbMTFMaE639sc3l";
        String dbUrl = "postgres://lqcbqfvsdadqgx:3sRCuOUA5BykbMTFMaE639sc3l@ec2-107-22-175-206.compute-1.amazonaws.com:5432/d26p0f71d1ckne";
        String driverClassName = "org.postgresql.Driver";
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setUrl(dbUrl);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }
}
