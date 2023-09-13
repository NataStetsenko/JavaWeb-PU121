package step.learning.db.dao;

import com.google.inject.name.Named;
import step.learning.servises.db.DbProvider;
import step.learning.servises.kdf.KdfService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class UserDao {
    private final DbProvider dbProvider;
    private final KdfService kdfService;
    private final Logger logger;
    private final String dbPrefix;

    @Inject
    public UserDao(DbProvider dbProvider, KdfService kdfService, Logger logger,
                   @Named("DbPrefix") String dbPrefix) {
        this.dbProvider = dbProvider;
        this.kdfService = kdfService;
        this.logger = logger;
        this.dbPrefix = dbPrefix;
    }

    /**
     * CREATE TABLE and INSERT first user
     */
    public void install() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + dbPrefix + "Users (" +
                "id               CHAR(36)     PRIMARY KEY," +
                "firstName        VARCHAR(50)  NULL," +
                "lastName         VARCHAR(50)  NULL," +
                "email            VARCHAR(128) NOT NULL," +
                "emailConfirmCode CHAR(6)      NULL," +
                "phone            VARCHAR(16)  NULL," +
                "phoneConfirmCode CHAR(6)      NULL," +
                "birthdate        DATETIME     NULL," +
                "avatar           VARCHAR(512) NULL," +
                "`login`          VARCHAR(64)  NOT NULL UNIQUE," +
                "salt             CHAR(8)      NOT NULL," +
                "passwordDk       VARCHAR(64)  NOT NULL COMMENT 'Derived Key (RFC 2898)'," +
                "registerDT       DATETIME     DEFAULT  CURRENT_TIMESTAMP," +
                "lastLoginDT      DATETIME     NULL," +
                "culture          CHAR(5)      NULL COMMENT 'uk-UA'," +
                "gender           VARCHAR(64)  NULL," +
                "banDT            DATETIME     NULL," +
                "deleteDT         DATETIME     NULL," +
                "roleId           CHAR(36)     NULL" +
                ") Engine = InnoDB  DEFAULT CHARSET = utf8";

        String id = "3c15b925-5216-11ee-a481-2a78e6f43b3a";  // UUID.randomUUID().toString() ;
        String salt = id.substring(0, 8);
        String defaultPassword = "123";
        String passwordDk = kdfService.getDerivedKey(defaultPassword, salt);

        String insertSQL = String.format(
                "INSERT INTO %1$sUsers (id, email, `login`, salt, passwordDk) " +
                        "VALUES( '%2$s', 'admin@some.mail.com', 'admin', '%3$s', '%4$s' ) " +
                        "ON DUPLICATE KEY UPDATE salt = '%3$s', passwordDk = '%4$s' ",
                dbPrefix, id, salt, passwordDk
        );

        try (Statement statement = dbProvider.getConnection().createStatement()) {
            statement.executeUpdate(createTableSQL);
            statement.executeUpdate(insertSQL);
        } catch (SQLException ex) {
            logger.log(
                    Level.SEVERE,
                    ex.getMessage() + "--" + createTableSQL + "--" + insertSQL
            );
            throw new RuntimeException(ex);
        }
    }

    public void add(String firstName, String lastName, String email, String phone,
                    Date birthdate, String avatar, String login, String defaultPassword,
                    String culture, String gender, String roleId) throws SQLException {

        String insertSQL = "INSERT INTO " + dbPrefix + "Users " +
                "(id, firstName, lastName, " +
                "email, phone, birthdate, avatar, login, " +
                "salt, passwordDk, " +
                "registerDT, " +
                "culture, gender, roleId) " +
                "VALUES (UUID(), ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?)";
        PreparedStatement preparedStatement = dbProvider.getConnection().prepareStatement(insertSQL);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, phone);
        if (birthdate != null) {
            Timestamp timestamp = new Timestamp(birthdate.getTime());
            preparedStatement.setTimestamp(5, timestamp);
        }
        preparedStatement.setString(6, avatar);
        preparedStatement.setString(7, login);
        String id = UUID.randomUUID().toString();
        String salt = id.substring(0, 8);
        String passwordDk = kdfService.getDerivedKey(defaultPassword, salt);
        preparedStatement.setString(8, salt);
        preparedStatement.setString(9, passwordDk);
//      Timestamp timeNow = new Timestamp(System.currentTimeMillis());
//      preparedStatement.setTimestamp(10, timeNow);
        preparedStatement.setString(10, culture);
        preparedStatement.setString(11, gender);
        preparedStatement.setString(12, roleId);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void delete() throws SQLException {
        String idUser = "eb9eec1a-5230-11ee-adbe-1e04156b55ff";
        String deleteSQL = "DELETE FROM " + dbPrefix + "Users WHERE id = ?";
        PreparedStatement preparedStatement = dbProvider.getConnection().prepareStatement(deleteSQL);
        preparedStatement.setString(1, idUser);
        preparedStatement.executeUpdate();
    }

    public void update() {
        String updateSQL = "UPDATE " + dbPrefix + "Users SET " +
                "firstName = ?, lastName = ?, " +
                "email = ?, emailConfirmCode = ?, " +
                "phone = ?, phoneConfirmCode = ?, " +
                "birthdate = ?, " +
                "avatar = ?, `login` = ?, " +
                "salt = ?, passwordDk = ?, " +
                "registerDT = ?, lastLoginDT= ?," +
                "culture = ?, " + "gender = ?, " +
                "banDT = ?, " + "deleteDT = ?, " +
                "roleId = ?  WHERE id = ?";
    }
}
