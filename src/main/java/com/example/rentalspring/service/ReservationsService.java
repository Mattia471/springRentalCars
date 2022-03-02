package com.example.rentalspring.service;


import com.example.rentalspring.domain.Reservations;

import java.util.List;

public interface ReservationsService {

    public List < Reservations > getReservations();

    public void saveReservation(Reservations theReservation);

    public Reservations getReservation(int Id);

    public Reservations deleteReservation(int Id);
}
