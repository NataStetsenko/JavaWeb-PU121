package step.learning.ioc;

import com.google.inject.AbstractModule;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class LoggerConfig extends AbstractModule {
    @Override
    protected void configure() {
        try(InputStream propertiesStream =
                this.getClass().
                getClassLoader().
                getResourceAsStream("logging.properties"
                )
        ) {
            LogManager logManager = LogManager.getLogManager();
            logManager.reset();
            logManager.readConfiguration(propertiesStream);
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
