package step.learning.email;

public interface EmailService {
    javax.mail.Message prepareMessage();
    void send(javax.mail.Message message);
}
