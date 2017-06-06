package by.goncharov.nc.dao.impl;

import by.goncharov.nc.abstracts.AbstractDAO;
import by.goncharov.nc.constants.Queries;
import by.goncharov.nc.dao.ICourseDao;
import by.goncharov.nc.entities.Course;
import by.goncharov.nc.exceptions.DAOUnException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ivan on 02.06.2017.
 */
@Repository
public class CourseDaoHibernate extends AbstractDAO<Course> implements ICourseDao {

    private static final Logger logger = Logger.getLogger(CourseDaoHibernate.class);


    @Autowired
    public CourseDaoHibernate(SessionFactory sessionFactory) {
        super(Course.class, sessionFactory);
    }

    @Override
    public Course getByName(String name) throws DAOUnException {
        Course course;
        try{
            Session session = getCurrentSession();
             Query query = session.createQuery(Queries.GET_BY_COURSE_NAME);
            query.setParameter("name", name);
            course = (Course) query.uniqueResult();
        } catch (Exception e) {
        logger.error("Unable to return name." + e);
        throw new DAOUnException("Unable to return name." + e);
    }
        return course;
    }




    @Override
    public List<Course> getAllCourseByUserId(int id) {
        List<Course> cor = null;
        try{
            Session session = getCurrentSession();
            Query query = session.createQuery(Queries.GET_BY_USERID);
            query.setParameter("id", id);


        } catch (HibernateException e) {
            logger.error("Error was thrown in DAO" + e);
            throw new DAOUnException();
        }
        return cor;
    }






    }

