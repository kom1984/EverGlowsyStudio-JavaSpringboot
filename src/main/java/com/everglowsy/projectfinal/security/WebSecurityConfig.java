package com.everglowsy.projectfinal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Qualifier("userService")
    @Autowired
private UserDetailsService userService;

        @Bean
        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> requests
                        .requestMatchers( "/","/signup", "/js/**", "/images/**", "/css/**").permitAll() // Permit access to these URLs
                        .requestMatchers("/admin/**").hasAuthority("ADMIN") // Requires ADMIN authority for admin URLs
                      //  .requestMatchers("/admin/all","/admin/Appointments").hasRole("EMPLOYEE") // Requires EMPLOYEE authority for admin URLs
                        .requestMatchers("/bookService","/bookService/**").hasAuthority("USER") // Requires authentication for appointment URLs
                        .anyRequest().authenticated() // Requires authentication for any other request
                )
                .formLogin(form -> form.loginPage("/login").usernameParameter("email").defaultSuccessUrl("/",true)
                        .permitAll())
                .logout(logout->logout.permitAll());
                return http.build();
    }


    @Autowired
        void configure(AuthenticationManagerBuilder builder) throws Exception {
            builder.userDetailsService(userService);
        }

}



