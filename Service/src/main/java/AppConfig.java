import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
/**
 * Created by Ôëþð on 11.07.2015.
 */
@Configuration
@ComponentScan(basePackages = "ru.fsep.enterprise.fseper")
public class AppConfig {
    @Bean
    public javax.sql.DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        String userName = " ";
        String password = " ";
        String dbUrl = "";
        String driverClassName = "";
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setUrl(dbUrl);
        dataSource.setDriverClassName(driverClassName);

        return  dataSource;
    }
}
