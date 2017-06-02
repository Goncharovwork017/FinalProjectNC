package by.goncharov.nc.dao.impl;

import by.goncharov.nc.abstracts.AbstractDAO;
import by.goncharov.nc.dao.inter.ICourseDAO;
import by.goncharov.nc.entities.Course;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ivan on 01.06.2017.
 */

@Repository
public class CourseDAOHibernate extends AbstractDAO<Course> implements ICourseDAO {

    private static final Logger logger = Logger.getLogger(CourseDAOHibernate.class);


    @Autowired
    public CourseDAOHibernate(SessionFactory sessionFactory) {
        super(Course.class, sessionFactory);
    }



}
