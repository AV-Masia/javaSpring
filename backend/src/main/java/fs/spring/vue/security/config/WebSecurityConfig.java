package fs.spring.vue.security.config;

import fs.spring.vue.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private CryptConfiguration cryptConfiguration;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(cryptConfiguration.passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
            .authorizeRequests()
            .antMatchers("/index", "/index.html").permitAll()
            .antMatchers("/api/login", "/api/logout",
                    "/api/registration", "/api/get_all_genres",
                    "/api/get_all_movies", "/api/filter_movies_by_genre", "/api/admin/delete_genre", "/api/get_genre_by_name").permitAll()
            .antMatchers("/api/admin/upload_movies", "/api/admin/delete_all_movies").hasAuthority("ADMIN")
//                .antMatchers("/api/user", "/api/deleteUser").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/api/user", "/api/deleteUser").permitAll()
            .antMatchers(
                    "/resources/**",
                    "/static/**",
                    "/css/**",
                    "/js/**",
                    "/assets/**",
                    "/favicon.ico")
                .permitAll()
            .anyRequest().authenticated();
        http.apply(jwtConfig);

    }

}
