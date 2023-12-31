package step.learning.db.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import step.learning.db.dto.User;
import step.learning.services.db.DbProvider;
import step.learning.services.kdf.KdfService;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
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
     * Authentication of user
     *
     * @param login
     * @param password
     * @return UserDTO or null
     */
    public User authenticate(String login, String password) {
        // 1. Шукаємо користувача по логіну
        // 2. Вилучаємо сіль та ДК
        // 3. Генеруємо ДК з солі та паролю, перевіряємо рівність збереженому ДК
        // 4. TODO: Оновлюємо поле lastLoginDt
        String sql = "SELECT u.* FROM " + dbPrefix + "Users u WHERE u.`login` = ?";
        try (PreparedStatement prep = dbProvider.getConnection().prepareStatement(sql)) {
            prep.setString(1, login);
            ResultSet res = prep.executeQuery();
            if (res.next()) {  // є дані
                User user = new User(res);
                if (kdfService
                        .getDerivedKey(password, user.getSalt())
                        .equals(user.getPasswordDk())) {
                    return user;
                }
            }  // else - до кіця, де return null ;
        } catch (SQLException ex) {
            logger.log(
                    Level.SEVERE,
                    ex.getMessage() + "--" + sql
            );
            throw new RuntimeException(ex);
        }
        return null;
    }

    public void add(User user) {
        String sql = "INSERT INTO " + dbPrefix + "Users (`id`, `firstName`, `lastName`,`email`," +
                " `phone`,`birthdate`,`avatar`,`login`,`salt`,`passwordDk`,`registerDT`,`culture`,`gender`, " +
                "`emailConfirmCode`, `phoneConfirmCode`, `roleId`)" +
                " VALUES( ?, ?, ?, ?,?,?,?,?,?, ?, ?, ?,?,?,?,? )";
        try (PreparedStatement prep = dbProvider.getConnection().prepareStatement(sql)) {

            prep.setString(1, user.getId() == null ? UUID.randomUUID().toString() : user.getId().toString());
            prep.setString(2, user.getFirstName());
            prep.setString(3, user.getLastName());
            prep.setString(4, user.getEmail());
            prep.setString(5, user.getPhone());
            prep.setDate(6, new java.sql.Date(user.getBirthdate().getTime()));
            prep.setString(7, user.getAvatar());
            prep.setString(8, user.getLogin());
            prep.setString(9, user.getSalt());
            prep.setString(10, user.getPasswordDk());
            prep.setTimestamp(11, new java.sql.Timestamp(user.getRegisterDT().getTime()));
            prep.setString(12, user.getCulture());
            prep.setString(13, user.getGender());
            prep.setString(14, user.getEmailConfirmCode());
            prep.setString(15, user.getPhoneConfirmCode());
            prep.setString(16, user.getRoleId() == null ? null : user.getRoleId().toString());
            prep.executeUpdate();


            Random random = new Random();
            int randomNumber = random.nextInt(999999) + 1;
            String randomString = String.format("%06d", randomNumber);


//            Properties emailProperties = new Properties();
//            emailProperties.put("mail.smtp.auth", "true");
//            emailProperties.put("mail.smtp.starttls.enable", "true");
//            emailProperties.put("mail.smtp.host", "smtp.gmail.com");
//            emailProperties.put("mail.smtp.port", "587");
//            emailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
//            emailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//            javax.mail.Session mailSession = javax.mail.Session.getInstance(emailProperties,
//                    new Authenticator() {
//                        @Override
//                        protected PasswordAuthentication getPasswordAuthentication() {
//                            return new PasswordAuthentication("natastetsenko44@gmail.com", "uxehuqlqodsigmdr");
//                        }
//                    }
//            );
//            MimeMessage message = new MimeMessage(mailSession);
//            try {
//                message.setFrom(new javax.mail.internet.InternetAddress("proviryalovich@gmail.com"));
//                message.setSubject("Реєстрація на JavaWeb");
//                message.setRecipients(
//                        Message.RecipientType.TO,
//                        InternetAddress.parse("natastetsenko44@gmail.com"));
//                MimeBodyPart htmlPart = new MimeBodyPart();
//                htmlPart.setContent("<b>Вітаємо</b> з реєстрацією <a href='http://localhost:8080/JavaWeb_PU121/'>на сайті!</a> Ваш код підтверження "+ randomString, "text/html; charset=UTF-8");
//                Multipart mailContent = new MimeMultipart();
//                mailContent.addBodyPart(htmlPart);
//                message.setContent(mailContent);
//                Transport.send(message);
//            } catch (MessagingException e) {
//                throw new RuntimeException(e);
//            }
//
        } catch (SQLException ex) {
            logger.log(
                    Level.SEVERE,
                    ex.getMessage() + "--" + sql
            );
            throw new RuntimeException(ex);
        }
    }
    public boolean confirmEmailCode( User user, String code ) {
        if( user == null
                || code == null
                || ! code.equals( user.getEmailConfirmCode() )     )
        {        return false ;    }
        String sql = "UPDATE " + dbPrefix + "Users SET emailConfirmCode = NULL WHERE id = ?";
        try (PreparedStatement preparedStatement = dbProvider.getConnection().prepareStatement(sql);)
        {
            preparedStatement.setString(1, user.getId().toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }}
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

        String id = "2e987203-5145-11ee-8444-966dc5fde598";  // UUID.randomUUID().toString() ;
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
            // statement.executeUpdate( insertSQL ) ;
        } catch (SQLException ex) {
            logger.log(
                    Level.SEVERE,
                    ex.getMessage() + "--" + createTableSQL + "--" + insertSQL
            );
            throw new RuntimeException(ex);
        }

    }

    public void delete() throws SQLException {
        String idUser = "a8147a62-01c9-44e7-aac4-ae42bfe7b2e1";
        String deleteSQL = "DELETE FROM " + dbPrefix + "Users WHERE id = ?";
        PreparedStatement preparedStatement = dbProvider.getConnection().prepareStatement(deleteSQL);
        preparedStatement.setString(1, idUser);
        preparedStatement.executeUpdate();
    }

    public void update(String idUser) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDate = sdf.format(new Date());
        String updateSQL = "UPDATE " + dbPrefix + "Users SET " +
                "lastLoginDT = ?  WHERE id = ?";
        PreparedStatement preparedStatement = dbProvider.getConnection().prepareStatement(updateSQL);

        preparedStatement.setString(1, newDate);
        preparedStatement.setString(2, idUser);
        preparedStatement.executeUpdate();
    }
//    public void update() {
//        String updateSQL = "UPDATE " + dbPrefix + "Users SET " +
//                "firstName = ?, lastName = ?, " +
//                "email = ?, emailConfirmCode = ?, " +
//                "phone = ?, phoneConfirmCode = ?, " +
//                "birthdate = ?, " +
//                "avatar = ?, `login` = ?, " +
//                "salt = ?, passwordDk = ?, " +
//                "registerDT = ?, lastLoginDT= ?," +
//                "culture = ?, " + "gender = ?, " +
//                "banDT = ?, " + "deleteDT = ?, " +
//                "roleId = ?  WHERE id = ?";
//    }
}
