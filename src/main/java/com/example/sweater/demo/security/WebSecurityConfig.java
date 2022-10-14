package com.example.sweater.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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

//    @Autowired
//    private JwtConfig jwtConfig;

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
                .antMatchers("/", "/login", "/logout", "/index", "/registrations")
                .permitAll();

        // /userInfo page requires login as ROLE_USER or ROLE_ADMIN.
        // If no login, it will redirect to /login page.
//        http.authorizeRequests().antMatchers("/accountInfo").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");

        // For ADMIN only.
//        http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
//        http.authorizeRequests().antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER");
//        http.authorizeRequests().antMatchers("/secure/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers("/basket/**").hasAnyAuthority("ROLE_USER");
//        http.authorizeRequests().antMatchers("/order/**").hasAnyAuthority("ROLE_USER", "ROLE_MANAGER");

        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will be thrown.
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/error");

        // Config for Login Form
        http.authorizeRequests()
                .and()
                .formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/index")//
//                .failureUrl("/login?error=true")//
                .failureUrl("/registration")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Config for Logout Page
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/index");

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
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/resources/**","/resources/js/**").permitAll()
//                .antMatchers("/css/**", "/js/**", "**/favicon.ico").anonymous()
//                .antMatchers("/", "/index").permitAll()
//                .antMatchers("/login").permitAll()
//                .anyRequest()
//                .authenticated()
////                .and()
////                .apply(jwtConfigurer);
//                .and()
//                .formLogin().loginPage("/login").permitAll()
//                .defaultSuccessUrl("/success")
//                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/index");
//    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    @Override
    public void configure(WebSecurity web) {
        web
            .ignoring()
            .antMatchers(
                    "/css/",
                    "/js/"
            );
    }
}
