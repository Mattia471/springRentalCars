package com.example.rentalspring.service;

import com.example.rentalspring.domain.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService
{
    static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UsersService usersService;

    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Users users = usersService.getEmailBySurname(email); //recupera  valore univoco
        logger.info("User : {}", users);
        if(users==null){
            logger.info("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(users.getEmail(), users.getPassword(),
                true,
                true,
                true,
                true,
                getGrantedAuthorities(users));
    }


    private List<GrantedAuthority> getGrantedAuthorities(Users users){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

            if(users.getIsAdmin()) //TRUE
            {
                authorities.add(new SimpleGrantedAuthority("ADMIN"));
            }
            else //FALSE
            {
                authorities.add(new SimpleGrantedAuthority("CUSTOMER"));
            }

        logger.info("authorities : {}", authorities);
        return authorities;
    }
}
