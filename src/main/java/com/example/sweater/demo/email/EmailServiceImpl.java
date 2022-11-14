package com.example.sweater.demo.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Locale;


@Service
public class EmailServiceImpl implements EmailService  {

    private final String EMAIL_TEMPLATE_ENCODING = StandardCharsets.UTF_8.name();

    @Autowired
    private EmailConfigProperties emailConfigProperties;

    @Autowired
    private TemplateEngine emailTemplateEngine;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendRegister(String recipientName, String recipientEmail) {

        Locale locale = Locale.ENGLISH;
        final Context ctx = new Context(locale);
        ctx.setVariable("name", recipientName);
        ctx.setVariable("subscriptionDate", new Date());

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, EMAIL_TEMPLATE_ENCODING);
            mimeMessageHelper.setSubject(emailConfigProperties.getRegister().getSubject());
            mimeMessageHelper.setFrom(emailConfigProperties.getFrom());
            mimeMessageHelper.setTo(recipientEmail);
            final String htmlContent = emailTemplateEngine.process(
                    emailConfigProperties.getRegister().getMessage(), ctx);
            mimeMessageHelper.setText(htmlContent, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        this.mailSender.send(mimeMessage);

    }
}
