package com.example.rentalspring.dao;

import com.example.rentalspring.domain.Cars;

import java.util.Date;
import java.util.List;

public interface CarsDao {
    public List<Cars> getCars();

    public List<Cars> getAvailableCars(Date dateFrom, Date dateTo);

    public void saveCar(Cars theCar);

    public Cars getCar(int id);

    public void deleteCar(int id);
}
