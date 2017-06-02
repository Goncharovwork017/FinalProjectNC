package by.goncharov.nc.dao.inter;


import by.goncharov.nc.entities.AbstractEntity;

import java.util.List;

/**
 * Created by ivan on 01.06.2017.
 */
public interface IDAO <T extends AbstractEntity> {

    List<T> getAll();

    int save(T entity);

    T getById(int id);

    void update(T entity);

    void delete(int id);
}
