package by.goncharov.nc.dao.inter;

import by.goncharov.nc.entities.Acc;
import by.goncharov.nc.exceptions.DAOUnException;

/**
 * Created by ivan on 01.06.2017.
 */
public interface IUserDAO extends IDAO<Acc> {


    Acc getByLogin(String login) throws DAOUnException;
    boolean isAuthorized(String login, String password) throws DAOUnException;
}
