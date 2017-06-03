package by.goncharov.nc.dao;

import by.goncharov.nc.entities.Course;

import java.util.List;

/**
 * Created by ivan on 02.06.2017.
 */
public interface ICourseDAO extends IDAO<Course> {


    List<Course> getAllCourseByUserId(int id);

    void startedCourse(Course course);
    void endedCourse(Course course);


}
