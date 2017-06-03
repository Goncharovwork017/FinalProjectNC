package by.goncharov.nc.service;

import by.goncharov.nc.dto.dto.CourseDTO;

import java.util.List;

/**
 * Created by ivan on 02.06.2017.
 */
public interface ICourseService extends IService<CourseDTO> {

    List<CourseDTO> findUsersCourse(int id);
    void endedCourse(CourseDTO courseDTO);
    void startedCourse(CourseDTO courseDTO);



}
