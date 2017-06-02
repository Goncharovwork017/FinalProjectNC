package by.goncharov.nc.services.inter;

import by.goncharov.nc.dto.dto.AbstractDTO;

import java.util.List;

/**
 * Created by ivan on 01.06.2017.
 */
public interface IService <T extends AbstractDTO>{


    int save(T entity);

    List<T> getAll();

    T getById(int id);

    void update(T entity);

    void delete(int id);
}
