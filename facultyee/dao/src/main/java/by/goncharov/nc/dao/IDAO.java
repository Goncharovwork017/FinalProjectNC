package by.goncharov.nc.dao;

import by.goncharov.nc.entities.AbstractEntity;

import java.util.List;

/**
 * Created by ivan on 02.06.2017.
 */
public interface IDao<T extends AbstractEntity>  {

    List<T> getAll();

    int save(T entity);

    T getById(int id);

    void update(T entity);

    void delete(int id);


}
