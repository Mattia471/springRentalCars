package com.example.rentalspring.service;

import com.example.rentalspring.dao.CarsDao;
import com.example.rentalspring.domain.Cars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service("carsService")
@Transactional
public class CarsServiceImpl implements CarsService {


    @Autowired
    private CarsDao carsDao;

    @Override
    @Transactional
    public List<Cars> getCars() {
        return carsDao.getCars();
    }

    @Override
    public List<Cars> getAvailableCars(Date dateFrom, Date dateTo) {
        return carsDao.getAvailableCars(dateFrom,dateTo);
    }

    @Override
    @Transactional
    public void saveCar(Cars theCar) {
        carsDao.saveCar(theCar);
    }

    @Override
    @Transactional
    public Cars getCar(int id) {
        return carsDao.getCar(id);
    }

    @Override
    @Transactional
    public Cars deleteCar(int id) {
        carsDao.deleteCar(id);
        return null;
    }

}
