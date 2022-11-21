package com.example.sweater.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtConfig jwtConfig;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
//    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        // The pages does not require login
        http.authorizeRequests()
                .antMatchers("/", "/login", "/logout", "/index", "/header", "/registration", "/reset").permitAll()
                .antMatchers( "/public/**","/templates/structure/**").permitAll()
                        .antMatchers("/resources/**","/resources/js/**","/resources/css/**").permitAll()
                        .antMatchers("/css/**", "/js/**", "**/favicon.ico").permitAll();

        // Config for Login Form
        http.authorizeRequests()
                .and()
                .formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")
                .defaultSuccessUrl("/index.html")
                .failureUrl("/login.html?error=true")
//                .failureUrl("/login.html")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/index");
//                                       .anyRequest()
//                            .authenticated()
//                            .and()
//                            .apply(jwtConfig);


        // Config Remember Me.
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(24 * 60 * 60); // 24h
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        // Web resource
//        web.ignoring().antMatchers("/resources/**");
//        web.ignoring().antMatchers("/resources/static/**");
//        web.ignoring().antMatchers("/resources/css/**");
//        web.ignoring().antMatchers("/resources/js/**");
//    }
}
