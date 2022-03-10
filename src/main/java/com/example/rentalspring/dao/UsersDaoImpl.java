package com.example.rentalspring.dao;


import com.example.rentalspring.domain.Users;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UsersDaoImpl extends AbstractDao<Users, Integer>
    implements UsersDao
{



    @Override
    public List< Users > getCustomers() {
        Session session = entityManager.unwrap(Session.class);

        //RESERVATIONS
        Criteria cbUsers = session.createCriteria(Users.class);

        Criterion q1U = Restrictions.eq("isAdmin",false);

        cbUsers.add(q1U);

        return (List<Users>) cbUsers.list();
    }



    @Override
    public void deleteCustomer(int id) {
        Session session = entityManager.unwrap(Session.class);
        Users book = session.byId(Users.class).load(id);
        session.delete(book);
    }

    @Override
    public void saveCustomer(Users theCustomer) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theCustomer);
    }

    @Override
    public Users getEmailBySurname(String email) {
        Session session = entityManager.unwrap(Session.class);


        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery< Users > cq = cb.createQuery(Users.class);

        Root< Users > root = cq.from(Users.class);
        ParameterExpression<String> f = cb.parameter(String.class);
        cq.select(root).where(cb.like(root.get("email"), f));

        TypedQuery<Users> query = session.createQuery(cq);
        query.setParameter(f, email);
        Users username = query.getSingleResult();

        return username;
    }

    @Override
    public List<Users> getByString(String filter) {
        Session session = entityManager.unwrap(Session.class);


        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery< Users > cq = cb.createQuery(Users.class);

        Root< Users > root = cq.from(Users.class);
        ParameterExpression<String> f = cb.parameter(String.class);
        cq.select(root).where(cb.like(root.get("name"), f));

        TypedQuery<Users> query = session.createQuery(cq);
        query.setParameter(f, filter);
        List<Users> results = query.getResultList();

        return results;
    }

    @Override
    public Users getCustomer(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Users theCustomer = currentSession.get(Users.class, theId);
        return theCustomer;
    }
}
