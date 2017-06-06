package by.goncharov.nc.dao;

import by.goncharov.nc.entities.Course;
import by.goncharov.nc.exceptions.DAOUnException;

import java.util.List;

/**
 * Created by ivan on 02.06.2017.
 */
public interface ICourseDao extends IDao<Course> {

    Course getByName(String name) throws DAOUnException;
    List<Course> getAllCourseByUserId(int id);



}
