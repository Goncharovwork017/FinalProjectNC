package by.goncharov.nc.dto.converters;


import by.goncharov.nc.dto.dto.CourseDTO;
import by.goncharov.nc.dto.dto.SheetListDTO;
import by.goncharov.nc.dto.dto.UserALLDTO;
import by.goncharov.nc.dto.dto.UserDTO;
import by.goncharov.nc.entities.Acc;
import by.goncharov.nc.entities.Course;
import by.goncharov.nc.entities.Roles;
import by.goncharov.nc.entities.SheetList;
import by.goncharov.nc.enums.RolesType;

/**
 * Created by ivan on 26.05.2017.
 */
public class Converter {


    public static Acc userAllDtoToUser(UserALLDTO userALLDTO)
    {
        Acc acc = new Acc();
        acc.setId(userALLDTO.getId());
        acc.setFirstName(userALLDTO.getFirstName());
        acc.setLastName(userALLDTO.getLastName());
        acc.setLogin(userALLDTO.getLogin());
        return acc;
    }


    public static UserALLDTO usetToUserALLDTO(Acc acc){
        UserALLDTO userDTO = new UserALLDTO();
        userDTO.setId(acc.getId());
        userDTO.setFirstName(acc.getFirstName());
        userDTO.setLastName(acc.getLastName());
        userDTO.setLogin(acc.getLogin());
        return userDTO;
    }

    public static Acc userDtoToUser(UserDTO userDTO) {
        Acc acc = new Acc();
        acc.setId(userDTO.getId());
        acc.setFirstName(userDTO.getFirstName());
        acc.setLastName(userDTO.getLastName());
        acc.setLogin(userDTO.getLogin());
        acc.setPassword(userDTO.getPassword());
        Roles roles = new Roles();
        roles.setId(2);
        roles.setRolesName(RolesType.STUDENT);
        acc.setRoles(roles);

        return acc;
    }

    public static UserDTO userToUserDto(Acc acc) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(acc.getId());
        userDTO.setFirstName(acc.getFirstName());
        userDTO.setLastName(acc.getLastName());
        userDTO.setLogin(acc.getLogin());
        userDTO.setPassword(acc.getPassword());
        userDTO.setRoles(String.valueOf(acc.getRoles().getRolesName()));
        return userDTO;
    }





    public static Course courseDtoToCourse(CourseDTO courseDTO){
        Course course = new Course();
        course.setId(courseDTO.getId());
        course.setName(courseDTO.getName());
        course.setCourseDescription(courseDTO.getCourseDescription());
        course.setStatus(courseDTO.isStatus());

        Acc user = new Acc();
        user.setId(courseDTO.getUser());
        course.setAcc(user);


        return course;

    }

    public static CourseDTO courseToCourseDto(Course course){

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());

        courseDTO.setUser(course.getAcc().getId());

        courseDTO.setCourseDescription(course.getCourseDescription());
        courseDTO.setStatus(course.isStatus());

        return  courseDTO;

    }



    public static SheetList sheetDtoToSheet(SheetListDTO sheetListDTO){
        SheetList sheetList = new SheetList();
        sheetList.setId(sheetListDTO.getId());
        sheetList.setScore(sheetListDTO.getScore());
        sheetList.setShortComment(sheetListDTO.getShortComment());

        Acc user = new Acc();
        user.setId(sheetListDTO.getUser());
        sheetList.setAcc(user);

        Course course = new Course();
        course.setId(sheetListDTO.getCourse());
        sheetList.setCourse(course);

        return  sheetList;
    }


    public static SheetListDTO sheetToSheetDto(SheetList sheetList){
        SheetListDTO sheetListDTO = new SheetListDTO();
        sheetListDTO.setId(sheetList.getId());
        sheetListDTO.setScore(sheetList.getScore());
        sheetListDTO.setShortComment(sheetList.getShortComment());

        sheetListDTO.setUser(sheetList.getAcc().getId());

        sheetListDTO.setCourse(sheetList.getCourse().getId());

        return sheetListDTO;
    }




}
