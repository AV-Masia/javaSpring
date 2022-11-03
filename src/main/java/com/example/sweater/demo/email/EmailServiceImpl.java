package com.example.sweater.demo.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;


@Service
public class EmailServiceImpl implements EmailService  {

    @Autowired
    private EmailConfigProperties emailConfigProperties;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendRegister(String recipientName, String recipientEmail, String recipientPassword) throws MessagingException {



    }
}
