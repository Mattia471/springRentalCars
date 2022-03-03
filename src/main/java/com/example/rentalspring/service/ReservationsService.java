package com.example.rentalspring.service;


import com.example.rentalspring.domain.Reservations;

import java.util.List;

public interface ReservationsService {

    List < Reservations > getReservations();

    List <Reservations> getReservationsUsers(int id);

    void saveReservation(Reservations theReservation);

    Reservations getReservation(int Id);

    Reservations deleteReservation(int Id);
}
