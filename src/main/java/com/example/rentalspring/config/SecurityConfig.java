package com.example.rentalspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity //abilitazione sicurezza
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //criptazione dati sensibili
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //dettagli utenti
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserBuilder users = User.builder();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(); //lavora in memoria db

        //creazione utenti con autorizzazioni
        manager.createUser(
                users
                        .username("Mattia")
                        .password(new BCryptPasswordEncoder().encode("1234"))
                        .roles("customer")
                        .build()
        );

        manager.createUser(
                users
                        .username("Admin")
                        .password(new BCryptPasswordEncoder().encode("1234"))
                        .roles("customer", "admin")
                        .build()
        );

        return manager;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService()) //metodo dettagli utenti
                .passwordEncoder(passwordEncoder()); //quale codificatore di password si utilizza
    }


    //autorizzazioni utenti, quale elementi della webb app possono accedere

    //quali url puo accedere l'admin
    private static final String[] ADMIN_CLIENTI_MATCHER =
            {
                    "/listCustomer",
            };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/RentalSpring_war_exploded/login").permitAll() //chiunque può accedere al login
                .antMatchers("/RentalSpring_war_exploded/").hasAnyRole("ANONYMOUS", "customer")
                .antMatchers(ADMIN_CLIENTI_MATCHER).access("hasRole('admin')")
                .antMatchers("/RentalSpring_war_exploded/**").hasRole("customer")
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .failureUrl("/login?error")
                        .usernameParameter("userId")
                        .passwordParameter("password")
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/login?forbidden")
                .and()
                    .logout()
                    .logoutUrl("/login?logout")
               .and().csrf().disable(); //disabilitare csrf sono in fase di test MAI se operativo
    }
}
