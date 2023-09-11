package step.learning.ioc;

import com.google.inject.servlet.ServletModule;
import step.learning.filters.CharsetFilter;
import step.learning.filters.DbFilter;
import step.learning.filters.LogFilter;

public class FilterConfig extends ServletModule {
    @Override
    protected void configureServlets() {
        filter("/*").through(CharsetFilter.class);
        filter("/*").through(LogFilter.class);
        filter("/*").through(DbFilter.class);

    }
}
