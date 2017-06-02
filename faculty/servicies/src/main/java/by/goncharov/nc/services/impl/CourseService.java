package by.goncharov.nc.services.impl;

import by.goncharov.nc.constants.ServiceConstants;
import by.goncharov.nc.dao.inter.ICourseDAO;
import by.goncharov.nc.dto.converters.Converter;
import by.goncharov.nc.dto.dto.CourseDTO;
import by.goncharov.nc.entities.Course;
import by.goncharov.nc.services.inter.ICourseService;
import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 01.06.2017.
 */
@Service
@Transactional
public class CourseService implements ICourseService {

    @Autowired
    private ICourseDAO courseDAO;
    private final static Logger logger = Logger.getLogger(CourseService.class);

    @Autowired
    public CourseService(ICourseDAO courseDAO) {
        this.courseDAO = courseDAO;
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
    public CourseDTO getById(int id) {
        Course course = null;
        CourseDTO courseDTO = null;
        try{
            course = courseDAO.getById(id);
            if(course != null)
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
           courseDAO.update(course);
           logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
       } catch (ServiceException e) {
           logger.error(ServiceConstants.TRANSACTION_FAILED, e);
       }
    }

    @Override
    public void delete(int id) {
        try{
            courseDAO.delete(id);
            logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
        } catch (ServiceException e) {
            logger.error(ServiceConstants.TRANSACTION_FAILED, e);
        }
    }
}
