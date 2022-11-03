package com.example.sweater.demo.email;

import javax.mail.MessagingException;

public interface EmailService {

    void sendRegister(final String recipientName, final String recipientEmail, final String recipientPassword)
            throws MessagingException;
}
