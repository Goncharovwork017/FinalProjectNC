package by.goncharov.nc.service.impl;

import by.goncharov.nc.constants.ServiceConstants;
import by.goncharov.nc.dao.ICourseDAO;
import by.goncharov.nc.dto.converters.Converter;
import by.goncharov.nc.dto.dto.CourseDTO;
import by.goncharov.nc.entities.Course;
import by.goncharov.nc.exceptions.DAOUnException;
import by.goncharov.nc.exceptions.NotFoundException;
import by.goncharov.nc.service.ICourseService;
import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 02.06.2017.
 */
@Service
@Transactional
public class CourseServiceImpl implements ICourseService {

    private static Logger logger = Logger.getLogger(CourseServiceImpl.class);


    private ICourseDAO courseDAO;


    @Autowired
    protected CourseServiceImpl(ICourseDAO courseDAO) {

        this.courseDAO = courseDAO;
    }

    @Override
    public List<CourseDTO> getAll() {
        List<Course> courses = null;
        CourseDTO courseDTO = null;
        List<CourseDTO> courseDTOS = new ArrayList<CourseDTO>();
        if (logger.isDebugEnabled()) {
            logger.debug(ServiceConstants.TRANSACTION_DEBUG);
        }
        try {
            courses = courseDAO.getAll();
            for (Course course : courses){
                courseDTO = Converter.courseToCourseDto(course);
                courseDTOS.add(courseDTO);
            }
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }

        return courseDTOS;
    }

    @Override
    public int save(CourseDTO courseDTO) {
        Course course = Converter.courseDtoToCourse(courseDTO);
        int id = 0;
        try{
            id = courseDAO.save(course);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
        return id;
    }

    @Override
    public CourseDTO getById(int id) {
        Course course = null;
        CourseDTO courseDTO = null;
        try{
            course = courseDAO.getById(id);
            if(course == null){
                throw new NotFoundException("Course not found!");
            }
                courseDTO = Converter.courseToCourseDto(course);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
        return courseDTO;
    }

    @Override
    public void update(CourseDTO courseDTO) {
        Course course = Converter.courseDtoToCourse(courseDTO);
        try{
            Course actualCourse = courseDAO.getById(course.getId());
            if(actualCourse == null){
                throw new NotFoundException("Course not found!");
            }
            courseDAO.update(course);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
    }

    @Override
    public void delete(int id) {
        try{
            Course course = courseDAO.getById(id);
            if(course == null){
                throw new NotFoundException("Course not found!");
            }
            courseDAO.delete(id);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
    }

    @Override
    public List<CourseDTO> findUsersCourse(int id) {
        List<CourseDTO> courseDTOS = new ArrayList<CourseDTO>();
        CourseDTO courseDTO = null;
        if (logger.isDebugEnabled()) {
            logger.debug(ServiceConstants.TRANSACTION_DEBUG);
        }

        try {
            List<Course> userCourses = courseDAO.getAllCourseByUserId(id);
                userCourses = courseDAO.getAll();
                for (Course course : userCourses) {
                    courseDTO = Converter.courseToCourseDto(course);
                    courseDTOS.add(courseDTO);
                }

                logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
            } catch (ServiceException e) {
                logger.error(ServiceConstants.TRANSACTION_FAILED, e);
            }
        return courseDTOS;
        }








    @Override
    public void endedCourse(CourseDTO courseDTO) {
        Course currendCourse = Converter.courseDtoToCourse(courseDTO);
        try {
            Course course = courseDAO.getById(courseDTO.getId());
            if (course == null) {
                throw new NotFoundException("Course not found!");
            }
            courseDAO.endedCourse(currendCourse);
            logger.info("Course: " + currendCourse + " successfully ended!");
        } catch (DAOUnException e) {
            logger.error("Error was thrown in course service method course ended: " + e);
        }
    }

    @Override
    public void startedCourse(CourseDTO courseDTO) {
        Course currendCourse = Converter.courseDtoToCourse(courseDTO);
        try {
            Course course = courseDAO.getById(currendCourse.getId());
            if (course == null) {
                throw new NotFoundException("Course not found");
            }
            courseDAO.startedCourse(currendCourse);
            logger.info("Course: " + currendCourse + " successfully updated!");
        } catch (DAOUnException e) {
            logger.error("Error was thrown in course service method course update: " + e);
        }
    }
}
