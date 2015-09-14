package ru.fsep.enterprise.fseper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.fsep.enterprise.fseper.controllers.TasksController;
import ru.fsep.enterprise.fseper.controllers.UserController;

/**
 * Created by Ôëþð on 05.09.2015.
 */
@Configuration
public class WebAppConfig {
    @Bean(name="/")
    public UserController getUserController() {
        return new UserController();
    }

    @Bean(name="/")
    public TasksController getTaskCOntroller() {
        return new TasksController();
    }
}
