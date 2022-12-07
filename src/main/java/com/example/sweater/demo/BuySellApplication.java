package com.example.sweater.demo;

import com.example.sweater.demo.email.EmailConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.sweater.demo"}, exclude = { SecurityAutoConfiguration.class })
@EnableJpaRepositories("com.example.sweater.demo.repository")
@EnableConfigurationProperties({
        EmailConfigProperties.class})
//@EnableAutoConfiguration
//@ComponentScan({"com.example.sweater.demo.service", "com.example.sweater.demo.email", "com.example.sweater.demo.security"})
public class BuySellApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BuySellApplication.class, args);
   }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BuySellApplication.class);
    }
}


