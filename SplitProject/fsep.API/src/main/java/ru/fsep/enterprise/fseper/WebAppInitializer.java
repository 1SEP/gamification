package ru.fsep.enterprise.fseper;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by Ôëþð on 01.09.2015.
 */
public class WebAppInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(WebAppConfig.class);

        ServletRegistration.Dynamic registration =
                servletContext.addServlet("DispatcherServlet", new DispatcherServlet(applicationContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
