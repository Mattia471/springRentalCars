package com.example.rentalspring.service;

import com.example.rentalspring.dao.ReservationsDao;
import com.example.rentalspring.domain.Reservations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("reservationsService")
@Transactional
public class ReservationsServiceImpl implements ReservationsService
{


    @Autowired
    private ReservationsDao reservationsDao;

    @Override
    @Transactional
    public List <Reservations> getReservations() {
        return reservationsDao.getReservations();
    }

    @Override
    public List<Reservations> getReservationsUsers(int id) {
        return reservationsDao.getReservationsUsers(id);
    }

    @Override
    @Transactional
    public void saveReservation(Reservations theReservation) {
        reservationsDao.saveReservation(theReservation);
    }

    @Override
    @Transactional
    public Reservations getReservation(int id) {
        return reservationsDao.getReservation(id);
    }

    @Override
    public void approveReservation(int id) {
        reservationsDao.approveReservation(id);
    }

    @Override
    public void declineReservation(int id) {
        reservationsDao.declineReservation(id);
    }

    @Override
    @Transactional
    public Reservations deleteReservation(int id) {
        reservationsDao.deleteReservation(id);
        return null;
    }

}
