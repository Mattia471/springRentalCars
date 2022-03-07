package com.example.rentalspring.service;

import com.example.rentalspring.dao.UsersDao;
import com.example.rentalspring.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("usersService")
@Transactional
public class UsersServiceImpl implements UsersService
{


    @Autowired
    private UsersDao usersDao;

    @Override
    @Transactional
    public List < Users > getCustomers() {
        return usersDao.getCustomers();
    }

    @Override
    @Transactional
    public void saveCustomer(Users theCustomer) {
        usersDao.saveCustomer(theCustomer);
    }

    @Override
    public Users getEmailBySurname(String surname) {
        return usersDao.getEmailBySurname(surname);
    }

    @Override
    public List<Users> getByString(String filter) {
        return usersDao.getByString(filter);
    }

    @Override
    @Transactional
    public Users getCustomer(int id) {
        return usersDao.getCustomer(id);
    }

    @Override
    @Transactional
    public Users deleteCustomer(int id) {
        usersDao.deleteCustomer(id);
        return null;
    }

}
