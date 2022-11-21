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
    private Reset reset = new Reset();

    @Getter
    @Setter
    public class Register{
        private String subject;
        private String message;
    }

    @Getter
    @Setter
    public class Reset{
        private String [] subject;
        private String [] message;
    }
}
