package com.example.rentalspring.dao;


import com.example.rentalspring.domain.Cars;
import com.example.rentalspring.domain.Reservations;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
public class CarsDaoImpl extends AbstractDao<Cars, Integer>
        implements CarsDao {


    @Override
    public List<Cars> getCars() {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Cars> cq = cb.createQuery(Cars.class);
        Root<Cars> root = cq.from(Cars.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Cars> getAvailableCars(Date dateFrom, Date dateTo) {
        Session session = entityManager.unwrap(Session.class);

        //RESERVATIONS
        Criteria cbReservation = session.createCriteria(Reservations.class);


        Criterion q1R =
                Restrictions.and
                        (
                Restrictions.or(
                        Restrictions.between(
                                "startDate", dateFrom, dateTo),
                        Restrictions.between(
                                "endDate", dateFrom, dateTo)),
                                Restrictions.or(
                                        Restrictions.eq
                                            ("status", "CONFERMATA" ),
                                        Restrictions.eq
                                                ("status", "IN ATTESA" )));

        cbReservation.add(q1R);

        List<Reservations> listReservations = (List<Reservations>) cbReservation.list();

        //RECUPERO ID AUTO OCCUPATE
        List<Integer> carsId = new ArrayList<>(listReservations.size()); //array che conterr?? id car

        for (int c = 0; c < listReservations.size(); c++) {
            carsId.add(listReservations.get(c).getCar().getId()); //estrapolo e assegno id car nell'array
        }

        //GENERO AUTO NON OCCUPATE TRAMITE ID
        //CARS
        Criteria cbCars = session.createCriteria(Cars.class);

        //se l'array ?? vuoto imposta unico valore a 0
        if(carsId.isEmpty()){
            carsId= Collections.singletonList(0);
        }


            cbCars.add(

                    Restrictions.not(
                                            Restrictions.in
                                                    ("id", carsId )
                                    )
            );

        return (List<Cars>) cbCars.list();

    }

    @Override
    public void deleteCar(int id) {
        Session session = entityManager.unwrap(Session.class);
        Cars delete = session.byId(Cars.class).load(id);
        session.delete(delete);
    }

    @Override
    public void saveCar(Cars theCar) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theCar);
    }

    @Override
    public Cars getCar(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Cars theCar = currentSession.get(Cars.class, theId);
        return theCar;
    }
}
