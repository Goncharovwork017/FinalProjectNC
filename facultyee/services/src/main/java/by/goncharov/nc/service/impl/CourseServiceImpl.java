package by.goncharov.nc.service.impl;

import by.goncharov.nc.constants.ServiceConstants;
import by.goncharov.nc.dao.ICourseDao;
import by.goncharov.nc.dto.converters.Converter;
import by.goncharov.nc.dto.dto.CourseDto;
import by.goncharov.nc.entities.Course;
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

@Autowired
    private ICourseDao courseDAO;


    @Autowired
    protected CourseServiceImpl(ICourseDao courseDAO) {

        this.courseDAO = courseDAO;
    }

    @Override
    public List<CourseDto> getAll() {
        List<Course> courses = null;
        CourseDto courseDto = null;
        List<CourseDto> courseDtos = new ArrayList<CourseDto>();
        if (logger.isDebugEnabled()) {
            logger.debug(ServiceConstants.TRANSACTION_DEBUG);
        }
        try {
            courses = courseDAO.getAll();
            for (Course course : courses){
                courseDto = Converter.courseToCourseDto(course);
                courseDtos.add(courseDto);
            }
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }

        return courseDtos;
    }

    @Override
    public int save(CourseDto courseDto) {
        Course course = Converter.courseDtoToCourse(courseDto);
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
    public CourseDto getById(int id) {
        Course course = null;
        CourseDto courseDto = null;
        try{
            course = courseDAO.getById(id);
            if(course == null){
                throw new NotFoundException("Course not found!");
            }
                courseDto = Converter.courseToCourseDto(course);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
        return courseDto;
    }

    @Override
    public void update(CourseDto courseDto) {
        Course course = Converter.courseDtoToCourse(courseDto);
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
    public List<CourseDto> findUsersCourse(int id) {
        List<CourseDto> courseDtos = new ArrayList<CourseDto>();
        CourseDto courseDto = null;
        try {
            List<Course> userCourses = courseDAO.getAllCourseByUserId(id);
              //  userCourses = courseDAO.getAll();
                for (Course course : userCourses) {
                    courseDto = Converter.courseToCourseDto(course);
                    courseDtos.add(courseDto);
                }

                logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
            } catch (ServiceException e) {
                logger.error(ServiceConstants.TRANSACTION_FAILED, e);
            }
        return courseDtos;
        }

    @Override
    public CourseDto getByName(String name) {
        Course course = null;
        CourseDto courseDto = null;
        try{
            course = courseDAO.getByName(name);
            if(course != null)
                courseDto = Converter.courseToCourseDto(course);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
        return courseDto;
    }





    }

