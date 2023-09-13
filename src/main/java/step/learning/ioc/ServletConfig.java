package step.learning.ioc;

import com.google.inject.servlet.ServletModule;
import step.learning.servlets.*;

public class ServletConfig extends ServletModule {
    @Override
    protected void configureServlets() {
        serve( "/" ).with( HomeServlet.class ) ;
        serve( "/home" ).with( HomeServlet.class ) ;
        serve( "/jsp" ).with( JspServlet.class ) ;
        serve( "/daysOfWeek" ).with( DaysServlet.class ) ;
        serve( "/servlet" ).with( ServletServlet.class ) ;
        serve( "/partsOfUrl" ).with( UrlServlet.class ) ;
        serve( "/hash" ).with( HashServlet.class ) ;
        serve( "/install" ).with( InstallServlet.class ) ;
    }
}
