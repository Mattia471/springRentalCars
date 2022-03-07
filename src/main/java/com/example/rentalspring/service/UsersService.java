package com.example.rentalspring.service;

import com.example.rentalspring.domain.Users;

import java.util.List;

public interface UsersService {

    List < Users > getCustomers();

    void saveCustomer(Users theUser);

    Users getEmailBySurname(String surname);

    List < Users > getByString(String filter);

    Users getCustomer(int Id);

    Users deleteCustomer(int Id);
}
