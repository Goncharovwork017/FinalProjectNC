package by.goncharov.nc.dao.impl;

import by.goncharov.nc.abstracts.AbstractDAO;
import by.goncharov.nc.constants.Queries;
import by.goncharov.nc.dao.ICourseDAO;
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
public class CourseDAOHibernate extends AbstractDAO<Course> implements ICourseDAO {

    private static final Logger logger = Logger.getLogger(CourseDAOHibernate.class);


    @Autowired
    public CourseDAOHibernate(SessionFactory sessionFactory) {
        super(Course.class, sessionFactory);
    }

    @Override
    public List<Course> getAllCourseByUserId(int id) {
        List<Course> cards = null;
        try{
            Session session = getCurrentSession();
            Query query = session.createQuery(Queries.GET_BY_USERID);
            query.setParameter("UserID", id);
        } catch (HibernateException e) {
            logger.error("Error was thrown in DAO" + e);
            throw new DAOUnException();
        }
        return cards;
    }

    @Override
    public void startedCourse(Course course) {
        try{
            course.setStatus(true);
            Session session = getCurrentSession();
            session.merge(course);
        } catch (HibernateException e) {
            logger.error("Error was thrown in CourseDAOHibernate method startedCourse: " + e);
            throw new DAOUnException(e);
        }
    }

    @Override
    public void endedCourse(Course course) {
        try{
            course.setStatus(false);
            Session session = getCurrentSession();
            session.merge(course);
        } catch (HibernateException e) {
            logger.error("Error was thrown in CourseDAOHibernate method startedCourse: " + e);
            throw new DAOUnException(e);
        }




    }
}
