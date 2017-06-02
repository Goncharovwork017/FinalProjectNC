package by.goncharov.nc.abstracts;

import by.goncharov.nc.dao.inter.IDAO;
import by.goncharov.nc.entities.AbstractEntity;
import by.goncharov.nc.exceptions.DAOUnException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ivan on 01.06.2017.
 */
@SuppressWarnings("unchecked")
public abstract class AbstractDAO<T extends AbstractEntity> implements IDAO<T> {


    private static Logger logger = Logger.getLogger(AbstractDAO.class);


    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> persistentClass;

    public AbstractDAO(Class<T> persistentClass,SessionFactory sessionFactory) {
        this.persistentClass = persistentClass;
        this.sessionFactory = sessionFactory;
    }



    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<T> getAll() {
        List<T> results;

        try {
            Session session = getCurrentSession();
            Criteria criteria = session.createCriteria(persistentClass);
            results = criteria.list();

        } catch (HibernateException e) {
            logger.error("Error was thrown in DAO" + e);
            throw new DAOUnException();
        }
        return results;
    }

    @Override
    public int save(T entity) {
        int id = 0;
        try {
            Session session = getCurrentSession();
            session.save(entity);
            id = (int) session.getIdentifier(entity);

        }
        catch(HibernateException e) {
            logger.error("Error was thrown in DAO" + e);
            throw new DAOUnException("Error was thrown in DAO" + e);
        }
        return id;
    }

    @Override
    public T getById(int id) {
        T entity;
        try {
            Session session = getCurrentSession();
            entity = (T)session.get(persistentClass, id);
        }
        catch(HibernateException e){
            logger.error("Error was thrown in DAO" + e);
            throw new DAOUnException();
        }
        return entity;
    }

    @Override
    public void update(T entity) {
        try {
            Session session = getCurrentSession();
            session.update(entity);
        }
        catch(HibernateException e) {
            logger.error("Error was thrown in DAO" + e);
            throw new DAOUnException();
        }
    }

    @Override
    public void delete(int id) {

        try {
            Session session = getCurrentSession();
            T entity = (T) session.get(persistentClass, id);
            session.delete(entity);
        }
        catch(HibernateException e){
            logger.error("Error was thrown in DAO" + e);
            throw new DAOUnException();
        }
    }




}
