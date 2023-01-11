package fs.spring.vue.security;

import fs.spring.vue.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;
import java.util.Collections;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebMvc
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
//@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private CryptConfiguration cryptConfiguration;

//    @Bean
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }

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

//
//        http.authorizeRequests()
//                .antMatchers("/api/register", "/login", "/logout", "/index", "/registration", "/user", "/reset").permitAll()
//                .antMatchers("/api/**", "/api/filter_movies").permitAll()
//                .antMatchers("/api/admin/**").hasRole("ADMIN")
//                .antMatchers("/deleteUser").permitAll()
//                .antMatchers( "/public/**","/templates/structure/**").permitAll()
//                .antMatchers("/resources/**","/resources/js/**","/resources/css/**").permitAll()
//                .antMatchers("/css/**", "/js/**", "**/favicon.ico").permitAll();

//        http.cors().and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                    .antMatchers("/api/login", "/api/register", "/api/get_all_movies", "/api/admin/upload_movies").permitAll()
//                .anyRequest().authenticated();

        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
            .authorizeRequests()
            .antMatchers("/index.html").permitAll()
            .antMatchers("/api/login", "/api/logout").permitAll()
//            .antMatchers("/internal").hasAuthority("ADMIN")
            .antMatchers(
                    "/resources/**",
                    "/static/**",
                    "/css/**",
                    "/js/**",
                    "/images/**")
                .permitAll()
            .anyRequest().authenticated();
        http
            .authorizeRequests()
//                .and().formLogin()
//                // Submit URL of login page.
//                .loginProcessingUrl("/j_spring_security_check") // Submit URL
//                .loginPage("/login")
//                .failureUrl("/login.html?error=true")
//                .usernameParameter("username").passwordParameter("password")
            .and()
            .logout().logoutUrl("/logout").logoutSuccessUrl("/index");
        http.apply(jwtConfig);

//        http.authorizeRequests()
//                .and()
//                .formLogin()//
//                // Submit URL of login page.
//                .loginProcessingUrl("/j_spring_security_check") // Submit URL
//                .loginPage("/login")
//                .failureUrl("/login.html?error=true")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .and()
//                .logout()
//                .logoutUrl("/logout").logoutSuccessUrl("/index");
//                                       .anyRequest()
//                            .authenticated()
//                            .and()
//                            .apply(jwtConfig);

    }

//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedOrigins(Collections.singletonList(CorsConfiguration.ALL));
//        corsConfiguration.setAllowedMethods(Collections.singletonList(CorsConfiguration.ALL));
//        corsConfiguration.setAllowedHeaders(Collections.singletonList(CorsConfiguration.ALL));
//        corsConfiguration.setMaxAge(Duration.ofMinutes(10));
//        corsConfiguration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        return new CorsFilter(source);
//    }

//    @Bean
//    public FilterRegistrationBean<?> corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean<?> bean = new FilterRegistrationBean<>(new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
//    }
}
