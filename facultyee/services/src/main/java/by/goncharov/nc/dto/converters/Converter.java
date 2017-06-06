package by.goncharov.nc.dto.converters;


import by.goncharov.nc.dto.dto.CourseDto;
import by.goncharov.nc.dto.dto.SheetListDto;
import by.goncharov.nc.dto.dto.UserDto;
import by.goncharov.nc.entities.Acc;
import by.goncharov.nc.entities.Course;
import by.goncharov.nc.entities.Roles;
import by.goncharov.nc.entities.SheetList;
import by.goncharov.nc.enums.RolesType;

/**
 * Created by ivan on 26.05.2017.
 */
public class Converter {


    public static Acc userDtoToUser(UserDto userDto) {
        Acc acc = new Acc();
        acc.setId(userDto.getId());
        acc.setFirstName(userDto.getFirstName());
        acc.setLastName(userDto.getLastName());
        acc.setLogin(userDto.getLogin());
        acc.setPassword(userDto.getPassword());
        Roles roles = new Roles();
        roles.setId(2);
        roles.setRolesName(RolesType.STUDENT);
        acc.setRoles(roles);

        return acc;
    }

    public static UserDto userToUserDto(Acc acc) {
        UserDto userDto = new UserDto();
        userDto.setId(acc.getId());
        userDto.setFirstName(acc.getFirstName());
        userDto.setLastName(acc.getLastName());
        userDto.setLogin(acc.getLogin());
        userDto.setPassword(acc.getPassword());
        userDto.setRoles(String.valueOf(acc.getRoles().getRolesName()));
        return userDto;
    }





    public static Course courseDtoToCourse(CourseDto courseDto){
        Course course = new Course();
        course.setId(courseDto.getId());
        course.setName(courseDto.getName());
        course.setCourseDescription(courseDto.getCourseDescription());
        course.setStatus(courseDto.getStatus());

        Acc user = new Acc();
        user.setId(courseDto.getUser());
        course.setAcc(user);


        return course;

    }

    public static CourseDto courseToCourseDto(Course course){

        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setName(course.getName());

        courseDto.setUser(course.getAcc().getId());

        courseDto.setCourseDescription(course.getCourseDescription());
        courseDto.setStatus(course.getStatus());

        return courseDto;

    }



    public static SheetList sheetDtoToSheet(SheetListDto sheetListDto){
        SheetList sheetList = new SheetList();
        sheetList.setId(sheetListDto.getId());
        sheetList.setScore(sheetListDto.getScore());
        sheetList.setShortComment(sheetListDto.getShortComment());

        Acc user = new Acc();
        user.setId(sheetListDto.getUser());
        sheetList.setAcc(user);

        Course course = new Course();
        course.setId(sheetListDto.getCourse());
        sheetList.setCourse(course);

        return  sheetList;
    }


    public static SheetListDto sheetToSheetDto(SheetList sheetList){
        SheetListDto sheetListDto = new SheetListDto();
        sheetListDto.setId(sheetList.getId());
        sheetListDto.setScore(sheetList.getScore());
        sheetListDto.setShortComment(sheetList.getShortComment());

        sheetListDto.setUser(sheetList.getAcc().getId());

        sheetListDto.setCourse(sheetList.getCourse().getId());

        return sheetListDto;
    }




}
