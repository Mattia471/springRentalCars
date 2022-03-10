package com.example.rentalspring.service;

import com.example.rentalspring.domain.Users;
import com.example.rentalspring.dto.UsersDto;

import java.text.ParseException;
import java.util.List;

public interface UsersService {

    List < Users > getCustomers();

    void saveCustomer(UsersDto theUser) throws ParseException;

    Users getEmailBySurname(String email);

    List < Users > getByString(String filter);

    UsersDto getCustomer(int Id);

    Users deleteCustomer(int Id);
}
