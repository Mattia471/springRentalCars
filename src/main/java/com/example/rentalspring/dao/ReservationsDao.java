package com.example.rentalspring.dao;

import com.example.rentalspring.domain.Cars;
import com.example.rentalspring.domain.Reservations;
import com.example.rentalspring.domain.Users;

import java.util.Date;
import java.util.List;

public interface ReservationsDao
{
    List <Reservations> getReservations();

    List <Reservations> getReservationsUsers(int id);

    void saveReservation(Reservations theReservations);

    void editReservation(int theId, Date startDate, Date endDate, Users user, Cars carId);

    Reservations getReservation(int id);

    void approveReservation(int id);

    void declineReservation(int id);

    void deleteReservation(int id);
}
