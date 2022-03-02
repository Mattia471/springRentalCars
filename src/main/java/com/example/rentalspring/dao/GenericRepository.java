package com.example.rentalspring.dao;

import com.sun.istack.NotNull;

import java.io.Serializable;
import java.util.List;

//I = entità, E= chiave primaria
public interface GenericRepository<I extends Serializable,E extends Serializable>
{
    @NotNull
    List<I> SelAll();

    void Add(@NotNull I entity);

    void Update(@NotNull I entity);

    void Delete(@NotNull I entity);

    void DeleteById(@NotNull E Id);

    I SelById(@NotNull E Id);
}
