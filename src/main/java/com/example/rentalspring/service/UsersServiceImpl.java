package com.example.rentalspring.service;

import com.example.rentalspring.dao.UsersDao;
import com.example.rentalspring.domain.Users;
import com.example.rentalspring.dto.UsersDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service("usersService")
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDao usersDao;

    @Override
    @Transactional
    public List<Users> getCustomers() {
        return usersDao.getCustomers();
    }

    @Override
    @Transactional
    public void saveCustomer(UsersDto theCustomer) throws ParseException {
        Users user = usersDao.getCustomer(theCustomer.getId());

        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");

        user.setIsAdmin(theCustomer.getIsAdmin());
        user.setBirthdate(pattern.parse(theCustomer.getBirthdate())); //format birthdate

        //cambia la password solo se è diversa
        if (!StringUtils.isBlank(theCustomer.getPassword())) {
            user.setPassword(theCustomer.getPassword());
        }
        user.setEmail(theCustomer.getEmail());
        user.setName(theCustomer.getName());
        user.setSurname(theCustomer.getSurname());

        usersDao.saveCustomer(user);
    }

    @Override
    public Users getEmailBySurname(String email) {
        return usersDao.getEmailBySurname(email);
    }

    @Override
    public List<Users> getByString(String filter) {
        return usersDao.getByString(filter);
    }

    @Override
    @Transactional
    public UsersDto getCustomer(int id) {
        Users users = usersDao.getCustomer(id);

        UsersDto usersDto = new UsersDto();
        usersDto.setId(users.getId());
        usersDto.setIsAdmin(users.getIsAdmin());

        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");
        usersDto.setBirthdate(pattern.format(users.getBirthdate())); //format birthdate
        usersDto.setPassword("");
        usersDto.setEmail(users.getEmail());
        usersDto.setName(users.getName());
        usersDto.setSurname(users.getSurname());

        return usersDto;
    }

    @Override
    @Transactional
    public Users deleteCustomer(int id) {
        usersDao.deleteCustomer(id);
        return null;
    }

}
