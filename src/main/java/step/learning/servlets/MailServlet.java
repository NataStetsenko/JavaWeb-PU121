package step.learning.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import step.learning.email.EmailService;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Properties;

@Singleton
public class MailServlet extends HttpServlet {
    private final EmailService emailService;

    @Inject
    public MailServlet(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // метод викликається до того, як відбувається розподіл за doXxx методами,
        // тут є можливість вплинути на цей розподіл
        switch (req.getMethod().toUpperCase()) {
            case "MAIL":
                this.doMail(req, resp);
                break;
            case "PATCH":
                this.doPatch(req, resp);
                break;
            case "LINK":
                this.doLink(req, resp);
                break;
            default:
                super.service(req, resp);  // розподіл за замовчанням
        }
    }

    protected void doMail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties emailProperties = new Properties();
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        emailProperties.put("mail.smtp.port", "587");
        emailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        emailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        resp.getWriter().print("MAIL works");
        javax.mail.Session mailSession = javax.mail.Session.getInstance(emailProperties);
        mailSession.setDebug(true);  // виводити у консоль процес надсилання пошти
        try (Transport emailTransport = mailSession.getTransport("smtp")) {
            emailTransport.connect("smtp.gmail.com", "natastetsenko44@gmail.com", "uxehuqlqodsigmdr");
            javax.mail.internet.MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new javax.mail.internet.InternetAddress("proviryalovich@gmail.com"));
            message.setSubject("From site JavaWeb");
            message.setContent("Вітаємо з реєстрацією <a href='http://localhost:8080/JavaWeb_PU121/' >на сайті!</a>", "text/html; charset=UTF-8");
            // Надсилаємо його
            //emailTransport.sendMessage( message, InternetAddress.parse( "natastetsenko44@gmail.com" ) ) ;
            emailTransport.sendMessage(message, InternetAddress.parse("natastetsenko44@gmail.com"));
            resp.getWriter().print("MAIL sent");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Properties emailProperties = new Properties();
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        emailProperties.put("mail.smtp.host", "smtp.gmail.com");
        emailProperties.put("mail.smtp.port", "587");
        emailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        emailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        javax.mail.Session mailSession = javax.mail.Session.getInstance(emailProperties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("natastetsenko44@gmail.com", "uxehuqlqodsigmdr");
                    }
                }
        );
        MimeMessage message = new MimeMessage(mailSession);
        try {
            message.setFrom(new javax.mail.internet.InternetAddress("proviryalovich@gmail.com"));
            message.setSubject("Вітання JavaWeb");
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("natastetsenko44@gmail.com"));


            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent("<b>Вітаємо</b> з реєстрацією <a href='http://localhost:8080/JavaWeb_PU121/' >на сайті!</a>", "text/html; charset=UTF-8");
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent("Вітаємо з реєстраціє на сайті!", "text/html; charset=UTF-8");

            String name = "qwert.png";
            String path = URLDecoder.decode(this.getClass().getClassLoader().getResource(name).getPath());
//                        /D:/Working%20folder/Java/JavaWeb-PU121/target/JavaWeb-PU121/WEB-INF/classes/qwert.png

//           String path = "D:\\Working folder\\Java\\JavaWeb-PU121\\target\\classes\\qwert.png";
//         String path = "D:\\Working folder\\Java\\JavaWeb-PU121\\target\\JavaWeb-PU121\\WEB-INF\\classes";

//         ServletContext context = getServletContext();
//         String relativePath = "WEB-INF/classes/qwert.png";
//         String path = context.getRealPath(relativePath);
//                       D:\Working folder\Java\JavaWeb-PU121\target\JavaWeb-PU121\WEB-INF\classes\qwert.png


//            URL resourceUrl = getClass().getClassLoader().getResource("qwert.png");
//            String path2 = resourceUrl.getFile();
//            String path = path2.substring(1);
//                      D:/Working%20folder/Java/JavaWeb-PU121/target/JavaWeb-PU121/WEB-INF/classes/qwert.png

            MimeBodyPart filePart = new MimeBodyPart();
            filePart.setDataHandler(new DataHandler(new FileDataSource(path)));
            filePart.setFileName(name);

            Multipart mailContent = new MimeMultipart();
            //mailContent.addBodyPart(textPart);
            //mailContent.addBodyPart(htmlPart);
            mailContent.addBodyPart(filePart);
            message.setContent(mailContent);
            Transport.send(message);
            resp.getWriter().print("PATCH works");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doLink(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Message message = emailService.prepareMessage();
            message.setRecipients(
                    // Одержувачів також перекладаємо у повідомлення
                    Message.RecipientType.TO,
                    InternetAddress.parse("natastetsenko44@gmail.com"));
            message.setContent("<b>Вітаємо</b> з реєстрацією на <a href='http://localhost:8080/JavaWeb_PU121/'>сайті</a>!",
                    "text/html; charset=UTF-8");
            emailService.send(message);
            resp.getWriter().print("Multipart sent");
        } catch (Exception ex) {
            resp.getWriter().print(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageName", "mail");
        req.getRequestDispatcher("WEB-INF/_layout.jsp").forward(req, resp);
    }
}
/*
Робота з електронною поштою
Оскільки надсилання пошти є потенційно вразливим інструментом, реалізуємо його
нестандартним методом запиту "MAIL"
 */