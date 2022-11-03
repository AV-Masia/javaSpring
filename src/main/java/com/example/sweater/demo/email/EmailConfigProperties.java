package com.example.sweater.demo.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix="mail")
public class EmailConfigProperties {


    private String from;

    private Register register = new Register();

    @Getter
    @Setter
    private class Register {
        private String subject;
        private String message;
    }
}
