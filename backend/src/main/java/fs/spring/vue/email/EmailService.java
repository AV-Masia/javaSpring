package fs.spring.vue.email;

import javax.mail.MessagingException;

public interface EmailService {

    void sendRegister(final String recipientName, final String recipientEmail)
            throws MessagingException;

    void sendPassword(String firstName, String lastName, String email, String password);
}
