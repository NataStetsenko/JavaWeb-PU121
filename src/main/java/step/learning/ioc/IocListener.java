package step.learning.ioc;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import javax.servlet.ServletContextEvent;

public class IocListener extends GuiceServletContextListener {

//    @Override
//    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        super.contextInitialized(servletContextEvent);
//    }

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new FilterConfig(),
                new ServletConfig(),
                new LoggerConfig(),
                new ServiceConfig()
        );
    }
}
