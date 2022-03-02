package com.example.rentalspring.service;

import com.example.rentalspring.domain.Cars;
import com.example.rentalspring.domain.Users;

import java.util.Date;
import java.util.List;

public interface CarsService {

    public List<Cars> getCars();

    public List<Cars> getAvailableCars(Date dateFrom, Date dateTo);

    public void saveCar(Cars theCar);

    public Cars getCar(int id);

    public Cars deleteCar(int id);
}
