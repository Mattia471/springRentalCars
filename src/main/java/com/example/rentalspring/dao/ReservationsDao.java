package com.example.rentalspring.dao;

import com.example.rentalspring.domain.Reservations;

import java.util.List;

public interface ReservationsDao
{
    List <Reservations> getReservations();

    public void saveReservation(Reservations theReservations);

    public Reservations getReservation(int id);

    public void deleteReservation(int id);
}
