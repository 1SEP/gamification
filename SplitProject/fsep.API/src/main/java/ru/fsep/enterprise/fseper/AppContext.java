package ru.fsep.enterprise.fseper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by Ôëþð on 11.07.2015.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ru.fsep.enterprise.fseper")
public class AppContext extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        String userName = "lqcbqfvsdadqgx";
        String password = "3sRCuOUA5BykbMTFMaE639sc3l";
        String dbUrl = "jdbc:postgresql://ec2-107-22-175-206.compute-1.amazonaws.com:5432/d26p0f71d1ckne?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        String driverClassName = "org.postgresql.Driver";
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setUrl(dbUrl);
        dataSource.setDriverClassName(driverClassName);

        return dataSource;
    }
}
