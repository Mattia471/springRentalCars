package com.example.rentalspring.dao;

import com.example.rentalspring.domain.Users;

import java.util.List;

public interface UsersDao
{
    public List < Users > getCustomers();

    public void saveCustomer(Users theCustomer);

    Users getEmailBySurname(String surname);

    public List < Users > getByString(String filter);

    public Users getCustomer(int id);

    public void deleteCustomer(int id);
}
