package by.goncharov.nc.service;

import by.goncharov.nc.dto.dto.CourseDto;

import java.util.List;

/**
 * Created by ivan on 02.06.2017.
 */
public interface ICourseService extends IService<CourseDto> {

    List<CourseDto> findUsersCourse(int id);
//    void endedCourse(CourseDto courseDTO);
//    void startedCourse(CourseDto courseDTO);
    CourseDto getByName(String name);


}
