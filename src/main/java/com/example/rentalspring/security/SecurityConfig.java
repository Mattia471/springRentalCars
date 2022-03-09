package com.example.rentalspring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity //abilitazione sicurezza
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    //array url permessi alle tipologie di utenti
    private static final String[] ADMIN_PERMIT = {
            "/listCustomer" ,
            "/newCar",
            "/newCustomer",
            "/listReservationsUser",
            "/declineReservation",
            "/approveReservation"
    };

    private static final String[] CUSTOMER_PERMIT = {
            "/listReservations",
    };

    private static final String[] ALL_PERMIT = {
            "/listCar",
            "/profile",
            "/newReservation",
            "/myProfile"
    };


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //disable solo in fase di test
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers(CUSTOMER_PERMIT).access("hasRole('CUSTOMER')")
                .antMatchers(ADMIN_PERMIT).access("hasRole('ADMIN')")
                .antMatchers(ALL_PERMIT).permitAll()
                .and()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/")
                .failureUrl("/?error")
                .defaultSuccessUrl("/listCar")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .exceptionHandling().accessDeniedPage("/?forbidden")
                .and()
                .logout()
                .logoutUrl("/logout")
                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
