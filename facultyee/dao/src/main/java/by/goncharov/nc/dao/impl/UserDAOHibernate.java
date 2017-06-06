package by.goncharov.nc.dao.impl;

import by.goncharov.nc.abstracts.AbstractDAO;
import by.goncharov.nc.constants.Queries;
import by.goncharov.nc.dao.IUserDao;
import by.goncharov.nc.entities.Acc;
import by.goncharov.nc.exceptions.DAOUnException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ivan on 02.06.2017.
 */


@Repository
public class UserDaoHibernate extends AbstractDAO<Acc> implements IUserDao {

    private static final Logger logger = Logger.getLogger(UserDaoHibernate.class);

    @Autowired
    public UserDaoHibernate(SessionFactory sessionFactory) {
        super(Acc.class, sessionFactory);
    }

    @Override
    @Transactional
    public Acc getByLogin(String login) {
        Acc acc;

        try {

            Session session = getCurrentSession();
            Query query = session.createQuery(Queries.GET_BY_LOGIN);
            query.setParameter("login", login);
            acc = (Acc) query.uniqueResult();
        } catch (Exception e) {
            logger.error("Unable to return login." + e);
            throw new DAOUnException("Unable to return login." + e);
        }
        return acc;
    }

    @Override
    public boolean isAuthorized(String login, String password) throws DAOUnException {
        boolean isLogIn = false;
        try {
            Session session = getCurrentSession();
            Query query = session.createQuery(Queries.CHECK_AUTHORIZATION);
            query.setParameter("login", login);
            query.setParameter("password", password);
            if (query.uniqueResult() != null) {
                isLogIn = true;
            }
        } catch (HibernateException e) {
            logger.error("Unable to check authorization. Error was thrown in DAO: " + e);
            throw new DAOUnException("Unable to check authorization. Error was thrown in DAO: " + e);
        }
        return isLogIn;
    }
}
