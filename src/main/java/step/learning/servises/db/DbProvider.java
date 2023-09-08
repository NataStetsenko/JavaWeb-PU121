package step.learning.servises.db;

import java.sql.Connection;


public interface DbProvider {
    Connection getConnection();
}
