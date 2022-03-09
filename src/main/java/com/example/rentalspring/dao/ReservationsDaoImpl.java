package com.example.rentalspring.dao;


import com.example.rentalspring.domain.Cars;
import com.example.rentalspring.domain.Reservations;
import com.example.rentalspring.domain.Users;
import com.example.rentalspring.service.UsersService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Repository
public class ReservationsDaoImpl extends AbstractDao<Reservations, Integer>
    implements ReservationsDao
{

    private final UsersService usersService;

    public ReservationsDaoImpl(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public List< Reservations > getReservations() {
        Session session = entityManager.unwrap(Session.class);

        //recupera l'id dalla sessione del ContextHolder
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Users userId = usersService.getEmailBySurname(principal.getUsername());

        //RESERVATIONS
        Criteria cbReservation = session.createCriteria(Reservations.class);

        Criterion q1R = Restrictions.eq("user.id",userId.getId());

        cbReservation.add(q1R);

        return (List<Reservations>) cbReservation.list();
    }

    @Override
    public List<Reservations> getReservationsUsers(int id) {
        Session session = entityManager.unwrap(Session.class);

        //RESERVATIONS
        Criteria cbReservation = session.createCriteria(Reservations.class);

        Criterion q1R = Restrictions.eq("user.id",id);

        cbReservation.add(q1R);

        return (List<Reservations>) cbReservation.list();
    }

    @Override
    public void deleteReservation(int id) {
        Session session = entityManager.unwrap(Session.class);
        Reservations book = session.byId(Reservations.class).load(id);
        session.delete(book);
    }

    @Override
    public void saveReservation(Reservations theReservation) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theReservation);
    }

    @Override
    public void editReservation(int theId, Date startDate, Date endDate, Users user, Cars carId) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();

        // create update
        CriteriaUpdate<Reservations> update = cb.
                createCriteriaUpdate(Reservations.class);

        // set the root class
        Root reservationsRoot = update.from(Reservations.class);

        // set update and where clause
        update.set("car", carId);
        update.set("startDate", startDate);
        update.set("endDate", endDate);
        update.where(cb.equal(reservationsRoot.get("id"), theId));

        // perform update
        this.entityManager.createQuery(update).executeUpdate();
    }

    @Override
    public Reservations getReservation(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Reservations theReservation = currentSession.get(Reservations.class, theId);
        return theReservation;
    }

    @Override
    public void approveReservation(int id) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();

        // create update
        CriteriaUpdate<Reservations> update = cb.
                createCriteriaUpdate(Reservations.class);

        // set the root class
        Root reservationsRoot = update.from(Reservations.class);

        // set update and where clause
        update.set("status", "CONFERMATA");
        update.where(cb.equal(reservationsRoot.get("id"), id));

        // perform update
        this.entityManager.createQuery(update).executeUpdate();
    }

    @Override
    public void declineReservation(int id) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();

        // create update
        CriteriaUpdate<Reservations> update = cb.
                createCriteriaUpdate(Reservations.class);

        // set the root class
        Root reservationsRoot = update.from(Reservations.class);

        // set update and where clause
        update.set("status", "RIFIUTATA");
        update.where(cb.equal(reservationsRoot.get("id"), id));

        // perform update
        this.entityManager.createQuery(update).executeUpdate();
    }
}
