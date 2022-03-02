package com.example.rentalspring.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

//I classe entità,Id chiave primaria della classe
public abstract class AbstractDao<I extends Serializable,Id extends Serializable>
    implements GenericRepository<I,Id>
{
    @PersistenceContext //creazione contesto nella persistenza
    protected EntityManager entityManager;


    protected final Class<I> entityClass;

    CriteriaBuilder builder;

    @SuppressWarnings("unchecked")
    protected AbstractDao() {
        this.entityClass = (Class<I>) ((ParameterizedType)
                this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private CriteriaQuery<I> InitCriteria()
    {
        builder = this.entityManager.getCriteriaBuilder();
        return builder.createQuery(this.entityClass);
    }



    //metodi preso dall'interfaccia Generic
    @Override
    public List<I> SelAll() {
        CriteriaQuery<I> query = this.InitCriteria();

        //stiamo utilizzando i metodi di Criteria per fare un Select * from
        return this.entityManager.createQuery(
                query.select(query.from(this.entityClass))).getResultList();
    }


    @Override
    public I SelById(Id Id) {
        CriteriaQuery<I> query = this.InitCriteria();

        //stiamo utilizzando i metodi di Criteria per fare un Select * from where by id
        return this.entityManager.createQuery(
                query.where(
                        builder.equal(
                                query.from(this.entityClass).
                                        get("id"), Id)))
                .getSingleResult();

    }

    @Override
    public void Add(I entity) {
        this.entityManager.persist(entity);
        flushAndClear();
    }

    @Override
    public void Update(I entity) {
        this.entityManager.merge(entity);
        flushAndClear();
    }

    @Override
    public void Delete(I entity) {
        //se l'entità è presente nel db elimina con remove
        this.entityManager.remove(this.entityManager.contains(entity) ? entity : this.entityManager.merge(entity));
        flushAndClear();
    }

    @Override
    public void DeleteById(Id Id) {

        CriteriaQuery<I> query = this.InitCriteria();

        //stiamo utilizzando i metodi di Criteria per fare un Select * from where by id
        this.entityManager.createQuery(
                        query.where(
                                builder.equal(
                                        query.from(this.entityClass).
                                                get("id"), Id)))
                .executeUpdate();

        flushAndClear();
    }

    private void flushAndClear(){
        entityManager.flush();
        entityManager.clear();
    }
}
