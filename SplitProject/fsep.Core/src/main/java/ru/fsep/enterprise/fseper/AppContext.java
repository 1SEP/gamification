package ru.fsep.enterprise.fseper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by Ôëþð on 11.07.2015.
 */
@Configuration
@ComponentScan(basePackages = "ru.fsep.enterprise.ru.fsep.enterprise.fseper")
public class AppContext {
}
