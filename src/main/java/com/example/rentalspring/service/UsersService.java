package com.example.rentalspring.service;

import com.example.rentalspring.domain.Users;
import com.example.rentalspring.dto.UsersDto;

import java.util.List;

public interface UsersService {

    List < Users > getCustomers();

    void saveCustomer(Users theUser);

    Users getEmailBySurname(String email);

    List < Users > getByString(String filter);

    Users getCustomer(int Id);

    Users deleteCustomer(int Id);
}
