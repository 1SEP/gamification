package ru.fsep.enterprise.fseper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.fsep.enterprise.fseper.controllers.converters.TasksAndStepsConverter;
import ru.fsep.enterprise.fseper.controllers.converters.TasksAndStepsConverterImpl;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacade;
import ru.fsep.enterprise.fseper.service.facades.UsersServiceFacadeImpl;

/**
 * Created by ���� on 11.07.2015.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ru.fsep.enterprise.fseper")
public class AppContext extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

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


}