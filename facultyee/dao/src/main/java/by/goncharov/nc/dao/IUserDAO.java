package by.goncharov.nc.dao;

import by.goncharov.nc.entities.Acc;
import by.goncharov.nc.exceptions.DAOUnException;

/**
 * Created by ivan on 02.06.2017.
 */
public interface IUserDao extends IDao<Acc> {

    Acc getByLogin(String login) throws DAOUnException;

    boolean isAuthorized(String login, String password) throws DAOUnException;
}

