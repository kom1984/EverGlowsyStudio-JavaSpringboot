package com.everglowsy.projectfinal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
            http.authorizeHttpRequests((requests) -> requests
                            .requestMatchers("/","/signup", "/js/**","/images/**", "/css/**").permitAll()
                            .requestMatchers(antMatcher("/admin/**")).hasAuthority("ADMIN")
                            .requestMatchers(antMatcher("/appointment/**")).authenticated()
                            .anyRequest().authenticated()
                    )
                    .formLogin((form) -> form.loginPage("/admin").usernameParameter("email").defaultSuccessUrl("/admin/all").permitAll())
                   // .formLogin((form) -> form.loginPage("/appointment").permitAll())
                    .logout((logout) -> logout.permitAll());

            return http.build();


        }

        @Autowired
        void configure(AuthenticationManagerBuilder builder) throws Exception {
            builder.userDetailsService(userService);
        }
}



