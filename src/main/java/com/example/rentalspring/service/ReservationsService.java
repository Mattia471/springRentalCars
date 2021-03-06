package com.example.rentalspring.service;


import com.example.rentalspring.domain.Cars;
import com.example.rentalspring.domain.Reservations;
import com.example.rentalspring.domain.Users;

import java.util.Date;
import java.util.List;

public interface ReservationsService {

    List < Reservations > getReservations();

    List <Reservations> getReservationsUsers(int id);

    void saveReservation(Reservations theReservation);

    void editReservation(int theId, Date startDate, Date endDate, Users user, Cars carId);

    Reservations getReservation(int Id);

    void approveReservation(int id);

    void declineReservation(int id);

    Reservations deleteReservation(int Id);
}
