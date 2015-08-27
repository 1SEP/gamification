package ru.fsep.enterprise.fseper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.util.List;

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

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
        super.configureMessageConverters(converters);
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
