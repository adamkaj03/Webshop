package com.adam.buzas.onlab.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .authorizeRequests(configurer ->
                        configurer
                                .requestMatchers("/css/**",
                                                "/",
                                                "/bejelentkezes",
                                                "/konyv",
                                                "/kosar",
                                                "/regisztracio",
                                                "/kosarba",
                                                "/kosarbolTorol",
                                                "/kategoriaSzures",
                                                "/kereses",
                                                "/ujFelhasznalo"
                                        ).permitAll()
                                .requestMatchers("/elemFelvetel").hasRole("ADMIN")
                                .requestMatchers("/ujKonyv").hasRole("ADMIN")
                                .requestMatchers("/ujKategoria").hasRole("ADMIN")
                                .anyRequest().authenticated().and()
                )
                .formLogin(form ->
                        form
                                .loginPage("/bejelentkezes")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                )
                .logout(logout -> logout.permitAll().logoutSuccessUrl("/")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
