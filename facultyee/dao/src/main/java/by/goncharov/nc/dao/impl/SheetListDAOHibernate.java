package by.goncharov.nc.dao.impl;

import by.goncharov.nc.abstracts.AbstractDAO;
import by.goncharov.nc.constants.Queries;
import by.goncharov.nc.dao.ISheetListDao;
import by.goncharov.nc.entities.SheetList;
import by.goncharov.nc.exceptions.DAOUnException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ivan on 02.06.2017.
 */
@Repository
public class SheetListDaoHibernate extends AbstractDAO<SheetList> implements ISheetListDao {

    private static final Logger logger = Logger.getLogger(SheetListDaoHibernate.class);

    @Autowired
    public SheetListDaoHibernate(SessionFactory sessionFactory) {
        super(SheetList.class,sessionFactory);
    }

    @Override
    public SheetList getAllSheetByUserId(int id) {
        SheetList sheet = null;
        try{
            Session session = getCurrentSession();
            Query query = session.createQuery(Queries.GET_BY_USERID);
            query.setParameter("id", id);
            sheet = (SheetList) query.uniqueResult();
        } catch (HibernateException e) {
            logger.error("Error was thrown in DAO" + e);
            throw new DAOUnException();
        }
        return sheet;
    }

    @Override
    public SheetList getSheetByCourseIdAndUserId(int courseId, int userId, int score, int shortcomment) {
        SheetList sheet = null;
        try{
            Session session = getCurrentSession();
            Query query = session.createQuery(Queries.getSheetByCourseIdAndUserId);
            query.setParameter("userid", userId);
            query.setParameter("courseid", courseId);
            query.setParameter("score",score);
            query.setParameter("shortcomment",shortcomment);
            sheet = (SheetList) query.uniqueResult();
        } catch (HibernateException e) {
            logger.error("Error was thrown in DAO" + e);
            throw new DAOUnException();
        }
        return sheet;
    }






}
