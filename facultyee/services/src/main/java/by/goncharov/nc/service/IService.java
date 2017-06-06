package by.goncharov.nc.service;

import by.goncharov.nc.dto.dto.AbstractDto;

import java.util.List;

/**
 * Created by ivan on 02.06.2017.
 */
public interface IService <T extends AbstractDto> {
    List<T> getAll() ;

    int save(T entity);
    T getById(int id);
    void update(T entity);
    void delete(int id);
}