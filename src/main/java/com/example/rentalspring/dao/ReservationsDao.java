package com.example.rentalspring.dao;

import com.example.rentalspring.domain.Reservations;

import java.util.List;

public interface ReservationsDao
{
    List <Reservations> getReservations();

    void saveReservation(Reservations theReservations);

    Reservations getReservation(int id);

    void deleteReservation(int id);
}
